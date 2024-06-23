//package com.dmp.components;
//
//import com.dmp.services.ReceiptService;
//import com.dmp.services.CabinetService;
////import com.dmp.services.ContractSerivces;
//import com.dmp.services.ReceiptService;
//import com.dmp.services.RoomService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//@EnableScheduling
//@Component
//public class ScheduledTasks {
//
//    @Autowired
//    private CabinetService cabinetService;
//
//    @Autowired
//    private RoomService roomServices;
//
////    @Autowired
////    private BillService billService;
//
//    //0s 0m 7h daily monthly
//    @Scheduled(cron = "0 0 7 * * ?")
//    public void checkExpireContract() {
//        this.roomServices.activateRoomAfterContractClose();
//        this.cabinetService.closeExpiredContractCabinets();
//    }
//
//    @Scheduled(cron = "0 0 7 1 * ?")
//    public void runCreateBill(){billService.createAllBill();}
//}