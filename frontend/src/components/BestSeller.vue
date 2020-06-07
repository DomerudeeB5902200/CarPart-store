<template>
  <v-app>
    <v-card class="mx-auto" width="95%">
      <v-toolbar color="blue-grey" dark flat>
        <v-toolbar-title
          style="width:100%; text-align:center; color:white ; front-size:35px"
        >อะไหล่ขายดี</v-toolbar-title>
      </v-toolbar>
      <v-container>
        <v-card-text>
          <v-data-table :headers="headers" :items="sellers" item-key="id" class="elevation-1">
              <template
                  v-slot:item.order="{ item }"
                >{{sellers.map((x) => x[2]).indexOf(item[2]) + 1}}</template>
          </v-data-table>
        </v-card-text>
      </v-container>
    </v-card>
  </v-app>
</template>

<script>
import http from "../http-common";
export default {
data(){
    return{
        sellers:[],
        headers:[
            {
                text:"ลำดับขายดี",
                value:"order"
            },
            {
                text:"จำนวนที่ขายได้",
                value:"[0]"
            },
            {
                text:"รหัสสินค้าเดิม",
                value:"[1]"
            },
            {
                text:"รหัสสินค้าใหม่",
                value:"[2]"
            },
            {
                text:"ชื่อสินค้า",
                value:"[3]"
            }
        ]
    }
},
 methods: {
    getBestSeller() {
      http.get("/bestseller").then(response => {
        this.sellers = response.data;
        console.log(this.sellers);
      });
    },
},
mounted(){
    this.getBestSeller()
}
}
</script>