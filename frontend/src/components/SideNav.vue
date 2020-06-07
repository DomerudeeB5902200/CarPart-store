<template>
  <div style="display: flex">
    <v-card style="width: 20%;" class="side-nav-card" color="orange lighten-4">
      <v-list dense nav class="py-0" color="orange lighten-3">
        <v-list-item two-line>
          
          <v-list-item-avatar>
            <img src="@/assets/โปรไฟล์เว็บ.jpg"/>
            <!--<img src="@/assets/Dog Logo.png" />-->
          </v-list-item-avatar>

          <v-list-item-content style="color:black ; front-size:150px">
            <v-list-item-title>Domeruedee Rattanagosoomp</v-list-item-title>
            <v-list-item-subtitle>FaceBook : ปุยนุ่น โดมฤดี</v-list-item-subtitle>
          </v-list-item-content>
        </v-list-item>

        <v-divider></v-divider>

        <v-list-item v-for="item in items" :key="item.title" @click="goToLink(item.part)">
          <v-list-item-icon>
            <v-icon>{{ item.icon }}</v-icon>
          </v-list-item-icon>

          <v-list-item-content>
            <v-list-item-title>{{ item.title }}</v-list-item-title>
          </v-list-item-content>
        </v-list-item>
      </v-list>
    </v-card>
    <router-view style="width: 100%; margin-top: 20px"></router-view>
  </div>
</template>

<script>
export default {
  name: "SideNav",
  data() {
    return {
      items: [
       
        { title: "ค้นหาสินค้า", icon: "search", part: "/part" },
        { title: "สั่งซื้อสินค้า", icon: "add_shopping_cart", part: "/order" },
        { title: "บันทึกรายการอะไหล่", icon: "two_wheeler", part: "/carpart" },
        { title: "บันทึกการขาย", icon: "assignment", part: "/history" },
        { title: "อะไหล่ขายดี", icon: "schedule", part: "/bestseller" },
        { title: "ยอดขาย", icon: "trending_down", part: "/sales" }
      ]
    }
  },
  methods: {
    goToLink(part) {
      this.$router.push(part) //Part ต้องตรงกับหน้า index.js
    }
  }
}
</script>

<style scoped>
.side-nav-card {
  box-shadow: 0px 0px 0px 0px rgba(0, 0, 0, 0.2),
    1px 0px 2px 0px rgba(0, 0, 0, 0.14), 0px 0px 0px 0px rgba(0, 0, 0, 0.12);
}
</style>