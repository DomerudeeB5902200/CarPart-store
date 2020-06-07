<template>
  <v-app>
    <v-card class="mx-auto" width="95%">
      <v-toolbar color="blue-grey" dark flat>
        <v-toolbar-title style="width:100%; text-align:center; color:white ; front-size:35px">สั่งซื้อสินค้า</v-toolbar-title>
      </v-toolbar>
      <v-container>
        <v-row justify="center" style="align-items: center">
          <h4>ชนิดลูกค้า</h4>
          <v-radio-group v-model="customerType" :mandatory="false" row>
            <v-radio label="ทั่วไป" value="ทั่วไป"></v-radio>
            <v-radio label="เครดิต" value="เครดิต"></v-radio>
          </v-radio-group>
        </v-row>
        <v-row justify="center" style="align-items: baseline">
          <v-col class="text-right" size="5">
            <h4>ชื่อ-สกุล ลูกค้า</h4>
          </v-col>

          <v-col cols="7">
            <v-text-field
            style="width: 200px"
              v-model="customerName"
              outlined
              dense
              label="กรุณากรอกชื่อลูกค้า"
              required
              :rules="rules"
            ></v-text-field>
          </v-col>
        </v-row>

        <v-card-text>
          <v-data-table :headers="headers" :items="carparts" item-key="id" class="elevation-1">
            <template
              v-slot:item.IDBill="{ item }"
            >{{carparts.map((x) => x.carPartCarGenerationId).indexOf(item.carPartCarGenerationId) + 1}}</template>

            <template v-slot:item.deleteCarPart="{ item }">
              <v-icon @click="deleteCarPart(item)">delete</v-icon>
            </template>
            <template v-slot:item.totalPrice="{ item }">
              {{
              item.carPart.priceSell*item.amount
              }}
            </template>
          </v-data-table>

          <v-row style="justify-content:flex-end; align-items: baseline">
            <h4>ยอดรวมสุทธิ</h4>
            <v-col cols="2">
              <v-text-field reverse v-model="totalprice" outlined dense></v-text-field>
            </v-col>
            <h4>บาท</h4>
          </v-row>

          <v-row style="margin-top : 20px" justify="center" align="center">
            <v-btn color="grey" large dark flat @click="saveBill">บันทึกข้อมูล</v-btn>
            <!--<v-btn large color="grey" @click="saveBill">บันทึกข้อมูล</v-btn>-->
          </v-row>
        </v-card-text>
      </v-container>
    </v-card>
  </v-app>
</template>

<script>
import http from "../http-common"
export default {
  name: "Order",
  data: () => ({
    totalprice: 0,
    carparts: [],
    customerType: "ทั่วไป",
    rules: [value => !!value || "Required."],

    pic1:
      "http://getwallpapers.com/wallpaper/full/e/9/e/1012715-pastel-wallpaper-1920x1080-for-windows.jpg",
    customerName: "",
    RateUPX: [],
    headers: [
      {
        text: "ลำดับสินค้า",
        value: "IDBill"
      },
      {
        text: "จำนวน",
        value: "amount"
      },
      {
        text: "ชื่อรายการสินค้า",
        value: "carPart.carPartName"
      },
      {
        text: "รหัสสินค้า (รหัสเดิม)",
        value: "carPart.passOld"
      },
      {
        text: "ราคา / หน่วย",
        value: "carPart.priceSell"
      },
      {
        text: "จำนวนเงิน",
        value: "totalPrice"
      },
      {
        text: "นำสินค้าออก",
        value: "deleteCarPart",
        align: "center"
      }
    ]
  }),
  methods: {
    saveBill() {
      let totalPrice = 0
      let amount = 0

      this.$carPartService.carPart.forEach((carPart, index) => {
        totalPrice += carPart.carPart.priceSell * carPart.amount
        amount += Number(carPart.amount)
        this.$carPartService.carPart[index].carPart.amount = carPart.amount
      })
      console.log(this.$carPartService.carPart)
      http
        .post("/save/bill", {
          totalPrice: totalPrice,
          amount: amount,
          carPartCarGeneration: this.$carPartService.carPart,
          cusType: this.customerType,
          cusName: this.customerName
        })
        .then(response => {
          alert("เพิ่มข้อมูลสำเร็จ")
          console.log(response.data)
          this.$carPartService.carPart = []
          this.carparts = []
        })
    },
    deleteCarPart(carPart) {
      let index = this.$carPartService.carPart.findIndex(
        carPartInService =>
          carPartInService.carPartCarGenerationId ===
          carPart.carPartCarGenerationId
      )
      this.$carPartService.carPart.splice(index, 1) //สั่งให้เอาออก 1 ตัว
      this.carparts = this.$carPartService.carPart //อัพเดตตาราาง
    }
  },
  mounted() {
    this.carparts = this.$carPartService.carPart
    this.carparts.forEach(carPart => {
      console.log(carPart)
      this.totalprice += carPart.amount * carPart.carPart.priceSell
    })
  }
}
</script>