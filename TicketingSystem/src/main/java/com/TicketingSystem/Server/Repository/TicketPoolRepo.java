//package com.TicketingSystem.Server.Repository;
//
//import com.TicketingSystem.Server.Model.StatusTable;
//import org.springframework.data.domain.Example;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface TicketPoolRepo extends JpaRepository <StatusTable, String>{
//
////    @Override
////    boolean existsById(String name);
////@Query("SELECT CASE WHEN COUNT(a) > 0 THEN TRUE ELSE FALSE END FROM Activity a WHERE a.threadName = :threadName")
////boolean existsByThreadName(@Param("threadName") String threadName);
//
//
////    StatusTable findUserName(String name);
//
//
//    @Override
//    Optional<StatusTable> findById(String s);
//}