package com.telezone.controller;


import com.alibaba.fastjson.JSON;
import com.telezone.exception.CardIdArrTooLongException;
import com.telezone.model.*;
import com.telezone.service.InfoService;
import com.telezone.socket.SocketClient;
import com.telezone.socket.SocketResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author： 李树林
 * @description：
 */
@RestController
public class InfoController {

    @Autowired
    private InfoService infoService;


    @Autowired
    private SocketResource socketResource;

    private final Logger logger = LoggerFactory.getLogger(InfoController.class);

    @PostMapping(value="/v1/PersonPointDefine/GetPlsPointInfo")
    public Return getWord(){
        logger.info("getWord");
        Return<Device> deviceReturn = new Return();
        deviceReturn.setPointInfo(infoService.getDevice());
        logger.info("getWord return:",JSON.toJSONString(deviceReturn));
        return  deviceReturn;
    }

    @PostMapping(value="/v1/R_Call/PlsPointAlarm")
    public Result Call(@RequestParam ( "PlsPointSyncRequest")  String PlsPointSyncRequest){
        logger.info("call-str:"+PlsPointSyncRequest);
        List<CardReader> cardReaderCall = new ArrayList<>();
        List<CardReader> cardReaderStypCall = new ArrayList<>();
        Request PointInfo = JSON.parseObject(PlsPointSyncRequest, Request.class);
        if(PointInfo.getPointInfo() != null && PointInfo.getPointInfo().size() > 0){
            for (CallType callType : PointInfo.getPointInfo()) {
                CardReader cardReader = new CardReader();
                cardReader.setCardReaderId(Integer.valueOf(callType.getId()));
                cardReader.getCardIdArr().add(0);
                if(callType.getCallPersonDefType()==3){
                    cardReader.setCardReaderId(0);
                    if(callType.getCallType()==1){
                        cardReaderCall.add(cardReader);
                    }else{
                        cardReaderStypCall.add(cardReader);
                    }
                }else if(callType.getCallPersonDefType()==4){
                    String[] ids = callType.getFzhList().split(",");
                    for (String id : ids) {
                        cardReader.setCardReaderId(Integer.valueOf(id));
                        if(callType.getCallType()==1){
                            cardReaderCall.add(cardReader);
                        }else{
                            cardReaderStypCall.add(cardReader);
                        }
                    }

                }
            }
        }else{
            return Result.Error("呼叫列表为空");
        }
        if(cardReaderCall.size()>0){
            Thread th = new Thread(new SocketClient(socketResource.getIp(), socketResource.getPort(),
                    getSocketMsg(15372,"dds", BS2CSType.getCallType(1),2,cardReaderCall.size(), cardReaderCall)));
            th.start();
        }
       if(cardReaderStypCall.size()>0){
           Thread th = new Thread(new SocketClient(socketResource.getIp(), socketResource.getPort(),
                   getSocketMsg(15372,"dds", BS2CSType.getCallType(4),2,cardReaderCall.size(), cardReaderCall)));
           th.start();
       }
        logger.info("Call success");
        return Result.Ok();
    }

    /**
     *
     * @param Command 特征字
     * @param userID 用户ID
     * @param level 呼叫类型
     * @param continueTime 呼叫时间
     * @param readerCount 呼叫分站数量
     * @param readerArr 呼叫分站对象 里边有呼叫得卡号
     * @return
     */
    private static byte[] getSocketMsg (int Command, String userID, int level,
                                        int continueTime,int readerCount, List<CardReader> readerArr){
        // position 当前位置, step 步数
        int position = 0, step = 0,
                //总长度
                totalCount = BS2CSType.L_TYPE+BS2CSType.L_USERID+BS2CSType.L_CALLTYPE;
        if(continueTime!=BS2CSType.ABANDON)
            totalCount+=BS2CSType.L_COUNTINUTE;

        //计算协议总长度
        if(readerCount!=BS2CSType.ABANDON){
            int count = -9999;
            do {
                totalCount+=BS2CSType.L_CARDREADER;
                //&& callCardreaderNo!=BS2CSType.CALL_READER_NO_ALL
                if(count==-9999 && readerArr!=null)
                    count=readerArr.size();
                count--;

                for (CardReader reader :  readerArr) {
                    totalCount+=BS2CSType.L_CARDREADERID;
                    List<Integer> cardArr = reader.getCardIdArr();
                    if(cardArr!=null && cardArr.size()> 0){
                        if(cardArr.size()>Math.pow(2, 8))
                            throw new CardIdArrTooLongException("cardIDArr.size is too long。");
                        totalCount+=BS2CSType.L_CARDNO;
                        totalCount+=BS2CSType.L_CARDID;
                    }
                }
            } while (count>0);
        }

        //保存协议
        byte[] msg = new byte[totalCount+BS2CSType.L_TOTAL];

        //保存总长度
        byte[] totalCountBytes = Tools.intTo2Bytes(BS2CSType.L_TOTAL, totalCount|0xF000);
        System.arraycopy(totalCountBytes, 0, msg, position, BS2CSType.L_TOTAL);
        //光标位置
        step=BS2CSType.L_TOTAL;

        //保存特征字
        byte[] CommandBytes = Tools.intTo2Bytes(BS2CSType.L_TYPE,Command);
        System.arraycopy(CommandBytes, 0, msg, position+=step, BS2CSType.L_TYPE);
        step=BS2CSType.L_TYPE;

        //存储用户ID
        byte[] userIDBytes = userID.getBytes();
        System.arraycopy(userIDBytes, 0, msg, position+=step, userIDBytes.length);
        step=BS2CSType.L_USERID;

        //存数呼叫类型（呼叫级别）0x20一般呼叫; 0x40重要呼叫; 0x60紧急呼叫;
        byte[] callTypeBytes = Tools.int2Bytes(BS2CSType.L_CALLTYPE, level);
        System.arraycopy(callTypeBytes, 0, msg, position+=step, BS2CSType.L_CALLTYPE);
        step=BS2CSType.L_CALLTYPE;

        //呼叫持续时间 如果有输入不为-1 就使用时间
        if(continueTime!=BS2CSType.ABANDON){
            byte[] continueTimeBytes = Tools.intTo2Bytes(BS2CSType.L_COUNTINUTE, continueTime);
            System.arraycopy(continueTimeBytes, 0, msg, position+=step, BS2CSType.L_COUNTINUTE);
        }
        step=BS2CSType.L_COUNTINUTE;

        //如果呼叫分站数不为-1
        if(readerCount!=BS2CSType.ABANDON){

            totalCount+=BS2CSType.L_CARDREADER;
            //callCardreaderNo!=BS2CSType.CALL_READER_NO_ALL&&
            int count = 0;
            if(readerArr!=null)
                count=readerArr.size();
            for(int i = 0 ; i < count ; i++){
                byte[] readerCountBytes = Tools.int2Bytes(BS2CSType.L_CARDREADER, readerCount);
                System.arraycopy(readerCountBytes, 0, msg, position+=step, BS2CSType.L_CARDREADER);
                step=BS2CSType.L_CARDREADER;

                //callCardreaderNo!=BS2CSType.CALL_READER_NO_ALL&&
                if(readerArr!=null){
                    byte[] readerIDBytes = Tools.intTo2Bytes(BS2CSType.L_CARDREADERID, readerArr.get(i).getCardReaderId());
                    System.arraycopy(readerIDBytes, 0, msg, position+=step, BS2CSType.L_CARDREADERID);
                    step=BS2CSType.L_CARDREADERID;

                    List<Integer> cardArr = readerArr.get(i).getCardIdArr();
                    if(cardArr!=null && cardArr.size()>0){
                        byte[] cardCountBytes = Tools.int2Bytes(BS2CSType.L_CARDNO, cardArr.size());
                        System.arraycopy(cardCountBytes, 0, msg, position+=step, BS2CSType.L_CARDNO);
                        step=BS2CSType.L_CARDNO;

                        for (Integer CardId:cardArr ) {
                            byte[] cardIDBytes = Tools.intTo2Bytes(BS2CSType.L_CARDID, CardId);
                            System.arraycopy(cardIDBytes, 0, msg, position+=step, BS2CSType.L_CARDID);
                            step=BS2CSType.L_CARDID;
                        }
                    }

                }
            }
        }
        return msg;
    }
}
