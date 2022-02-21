package com.rt.dao;

import com.rt.pojo.Detail;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DetailDao {
    int insert(Detail detail);

    Detail selectDetailByReaderIdAndIsbn( @Param("readerId") int readerId,@Param("isbn") String isbn);

    int updateReturnDateByReaderIdAndIsbn(@Param("readerId") int readerId,@Param("isbn") String isbn,@Param("returnDate")java.sql.Date returnDate);

    List<Detail> selectDetailByIsbn(String isbn);
}
