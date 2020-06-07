import Vue from "vue";
import Router from "vue-router";
import Order from "../components/Order";
import History from "../components/History";
import CarPart from "../components/CarPart";
import part from "../components/part";
import BestSeller from "../components/BestSeller";
import Sales from "../components/Sales";
Vue.use(Router);

export default new Router({
  mode: "history",
  routes: [
    {
      path: "/order",
      name: "Order",
      component: Order
    },
    {
      path: "/history",
      name: "History",
      component: History
    },
    {
      path: "/carpart",
      name: "CarPart",
      component: CarPart
    },
    {
      path: "/part",
      name: "part",
      component: part
    },
    {
      path: "/",
      name: "part",
      component: part
    },
    {
      path: "/bestseller",
      name: "BestSeller",
      component: BestSeller
    }, 
    {
      path: "/sales",
      name: "Sales",
      component: Sales
    },
  ]
});
