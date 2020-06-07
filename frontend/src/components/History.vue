<template>
  <v-app>
    <v-card class="mx-auto" width="95%">
      <v-toolbar color="blue-grey" dark flat>
        <v-toolbar-title
          style="width:100%; text-align:center; color:white ; front-size:35px"
        >ประวัติการขาย</v-toolbar-title>
      </v-toolbar>
      <v-container>
        <v-card-text>
          <v-data-table :headers="headers" :items="bills" item-key="id" class="elevation-1">
            <template
              v-slot:item.billId="{ item }"
            >{{bills.map((x) => x.billId).indexOf(item.billId) + 1}}</template>

            <template v-slot:item.show="{ item }">
              <v-icon @click="show(item)">subdirectory_arrow_right</v-icon>
            </template>
            <template v-slot:item.sellDate="{ item }">{{formatDate(item.sellDate)}}</template>
          </v-data-table>
        </v-card-text>
      </v-container>
    </v-card>

    <v-row justify="center">
      <v-dialog v-model="dialog"  max-width="900px">
        <v-card class="mx-auto" width="95%">
          <v-toolbar color="blue-grey" dark flat>
            <v-toolbar-title
              style="width:100%; text-align:center; color:white ; front-size:35px"
            >แสดงรายการสินค้า</v-toolbar-title>
          </v-toolbar>
          <v-container>
            <v-card-text>
              <v-data-table :headers="carPartHeader" :items="listItem" item-key="id" class="elevation-1">
                <template
                  v-slot:item.idPartNo="{ item }"
                >{{listItem.map((x) => x.idPartNo).indexOf(item.idPartNo) + 1}}</template>

              </v-data-table>
            </v-card-text>
          </v-container>
        </v-card>
      </v-dialog>
    </v-row>
  </v-app>
</template>

<script>
import http from "../http-common";
export default {
  data() {
    return {
      dialog: false,
      listItem:[],
      bills: [],
      carPartHeader:[
        {
        
            text:"ลำดับรายการสินค้า",
            value: "idPartNo"
        },
         {
            text:"รหัสสินค้าเดิม",
            value: "passOld"
        },
        {
            text:"รหัสสินค้าใหม่",
            value: "passNew"
        },
        {
            text:"ชื่อสินค้า",
            value: "carPartName"
        },
        {
            text:"บริษัท",
            value: "company"
        },
        {
            text:"ราคาสินค้า",
            value: "priceSell"
        },
        {
            text:"รายละเอียด",
            value: "detail"
        },
      ],
      headers: [
        {
          text: "ลำดับรายการขายสินค้า",
          value: "billId"
        },
        {
          text: "จำนวนเงินรวม",
          value: "totalPrice"
        },
        {
          text: "จำนวนสินค้า",
          value: "amount"
        },
        {
          text: "วันที่ขาย",
          value: "sellDate"
        },
        {
          text: "ประเภทลูกค้า",
          value: "cusType"
        },
        {
          text: "ชื่อลูกค้า",
          value: "cusName"
        },
        {
          text: "รายละเอียดคำสั่งซื้อ",
          value: "show",
          align: "center"
        }
      ]
    };
  },
  methods: {
    getBills() {
      http.get("/bills").then(response => {
        this.bills = response.data;
        console.log(this.bills);
      });
    },
    show(item) {
      http.get("/get/carparts/"+item.billId).then(response =>{
        this.listItem = response.data;
        console.log(this.listItem);
        console.log(this.listItem.map(a => a.idPartNo).indexOf(3))
      })
      console.log(item);
      this.dialog = true;
    },
    formatDate(date) {
      console.log(date);
      return new Date(date)
        .toUTCString()
        .toString()
        .substring(0, 25);
    }
  },
  mounted() {
    this.getBills();
  }
};
</script>>