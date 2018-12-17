package com.telezone.repository;

import com.telezone.model.AreaInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface InfoRepository extends JpaRepository<AreaInfo, Integer> {


//    @Query(nativeQuery = true, value = "select a.*,b.CardNum,0,getdate() from (select a.cardid,a.CardReaderID,b.name,b.Occupation,b.Department,0 as CardReader," +
//            "CRName,'' MAC,'' IP, starttime,null Outtime, 3 ReaderState from locatedata_everyTimeMod a,staffer b, CardReader c" +
//            "where starttime>getdate()-3.0/1440 and a.cardid = b.cardid and a.CardReaderID = c.CardReaderID and  c.[ground]=0 )a," +
//            "(select count(*)CardNum,a.cardreaderid from locatedata_everyTimeMod a,CardReader c " +
//            "where   c.ground=0 and starttime>getdate()-3.0/1440 group by a.cardreaderid)b where a.cardreaderid = b.cardreaderid")
//    List<Object[]> getDevice();

    @Query(nativeQuery = true, value = "select a.*, b.CardNum,0 cardReader,getdate() readerTime from (select a.cardid,a.CardReaderID,b.name,b.Occupation,b.Department,0 as CardReader," +
            "CRName,'' MAC,'' IP, starttime,null Outtime, 3 ReaderState from locatedata_everyTimeMod a,staffer b, CardReader c " +
            "where starttime>getdate()-3.0/1440 and a.cardid = b.cardid and a.CardReaderID = c.CardReaderID and  c.[ground]=0 )a," +
            "(select count(*)CardNum,a.cardreaderid from locatedata_everyTimeMod a,CardReader c " +
            "where   c.ground=0 and a.cardreaderid = c.cardreaderid  and starttime>getdate()-3.0/1440 group by a.cardreaderid)b where a.cardreaderid = b.cardreaderid")
    List<Object[]> getDevice();
}
