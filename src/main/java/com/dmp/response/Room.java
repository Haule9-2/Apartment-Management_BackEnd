//package com.dmp.response;
//
//import com.dmp.pojo.RentalContract;
//import com.dmp.pojo.Resident;
//import com.dmp.pojo.Roommate;
//import lombok.*;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@AllArgsConstructor
//@Builder
//@NoArgsConstructor
//@Setter
//@Getter
//public class Room {
//    public class RoomRegister {
//        private int id;
//        private int idContract;
//        private String fullName;
//        private String dateOfBirth;
//        private String city;
//        private String ward;
//        private String address;
//        private Boolean gender;
//        private String identity;
//        private String phone;
//        private String email;
//        private String numberPlate;
//        private long securityDeposit;
//        private String startedDate;
//        private Integer totalMonth;
//        private List<Resident> list;
//        public RoomRegister(int n) {
//            list = new ArrayList<>();
//            for (int i = 0; i <= n - 1; i++) {
//                list.add(new Resident());
//            }
//        }
//        public RoomRegister(RentalContract contract, List<Roommate> roommateList, int n){
//            list = new ArrayList<>();
//            for(int i=0;i<=n - 1;i++){
//                if(i <roommateList.size()){
//                    list.add(roommateList.get(i).getResidentUser());
//                }else{
//                    listMember.add(new Resident());
//                }
//            }
//
//    }
//}
