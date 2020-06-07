<template>
  <v-app>
    <v-card class="mx-auto" width="95%">
      <v-toolbar color="blue-grey" dark flat>
        <v-toolbar-title
          style="width:100%; text-align:center; color:white ; front-size:35px"
        >แสดงยอดขาย</v-toolbar-title>
      </v-toolbar>
      <v-form ref="salesForm">
        <v-card-text>
          <v-row justify="center" align="center">
            <v-col cols="6">
              <v-row justify="center" align="center">
                <v-date-picker @change="selectDate" v-model="dates" range></v-date-picker>
              </v-row>
              <v-row justify="center" align="center" style="margin-top:20px">
                <v-btn color="orange" large flat @click="searchTotalPrice">ค้นหายอดขาย</v-btn>
              </v-row>
            </v-col>

            <v-col cols="3">
              <v-text-field label="ยอดรวม" type="text" v-model="totalSale"></v-text-field>
            </v-col>
          </v-row>
        </v-card-text>
      </v-form>
    </v-card>
  </v-app>
</template>
<script>
import http from "../http-common"
export default {
  data() {
    return {
      totalSale: "",
      dates: [
        new Date().toISOString().substr(0, 10),
        new Date().toISOString().substr(0, 10)
      ]
    }
  },
  methods: {
    selectDate() {
      console.log(this.dates)
    },
    searchTotalPrice() {
      if (this.dates[0] > this.dates[1]) {
        const tempStartDate = this.dates[1]
        this.dates[1] = this.dates[0]
        this.dates[0] = tempStartDate
      }
      let endDate = new Date(this.dates[1])
      let nextDate = new Date(
        endDate.getFullYear(),
        endDate.getMonth(),
        endDate.getDate() + 2
      )
        .toISOString()
        .substr(0, 10)
      http
        .get("/searchtotalprice/" + this.dates[0] + "/" + nextDate)
        .then(response => {
          const amount = response.data ? response.data : 0
          this.totalSale = amount + "  บาท"
        })
    }
  }
}
</script>>