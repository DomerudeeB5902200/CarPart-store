<template>
  <v-app>
    <v-card class="mx-auto" width="95%">
      <v-toolbar color="blue-grey" dark flat>
        <v-toolbar-title
          style="width:100%; text-align:center; color:white ; front-size:35px"
        >บันทึกรายการอะไหล่</v-toolbar-title>
      </v-toolbar>
      <v-form ref="carPartForm">
        <v-card-text>
          <v-row justify="center" align="center">
            <v-col cols="4">
              <v-text-field outlined dense label="รหัสเดิม" type="text" v-model="carPartForm.PassOld" :rules="rules.PassOld"></v-text-field>
            </v-col>

            <v-col cols="4">
              <v-text-field outlined dense label="รหัสใหม่" type="text" v-model="carPartForm.PassNew" :rules="rules.PassNew"></v-text-field>
            </v-col>

            <v-col cols="4">
              <v-text-field
                outlined
                dense
                label="Part No."
                type="text"
                v-model="carPartForm.PartNo"
                disabled="true"
              ></v-text-field>
            </v-col>
          </v-row>

          <v-row justify="center" align="center">
            <v-col cols="6">
              <v-text-field label="ชื่อสินค้า" type="text" v-model="carPartForm.NameItem" :rules="rules.NameItem"></v-text-field>
            </v-col>

            <v-col cols="6">
              <v-text-field label="บริษัทที่สั่งซื้อ" type="text" v-model="carPartForm.Company" :rules="rules.Company"></v-text-field>
            </v-col>
          </v-row>

          <v-row justify="center" align="center">
            <v-col cols="4">
              <v-combobox
                v-model="carPartForm.CarTypeId"
                :rules="rules.CarTypeId"
                :items="CarTypes"
                item-text="carTypeName"
                v-on:change="filterCarType"
                item-value="carTypeId"
                label="ประเภทรถ"
              ></v-combobox>
            </v-col>

            <v-col cols="4">
              <v-combobox
                v-model="carPartForm.CarLabelId"
                :rules="rules.CarLabelId"
                :items="CarLabels"
                item-text="carLabelName"
                v-on:change="filterCarLabel"
                item-value="carLabelId"
                label="ยี่ห้อรถ"
              ></v-combobox>
            </v-col>

            <v-col cols="4">
              <v-combobox
                v-model="carPartForm.CarGenerationId"
                :rules="rules.CarGenerationId"
                :items="tempCarGenerations"
                item-text="generationCarName"
                item-value="CarGenerationId"
                label="รุ่นรถ"
              ></v-combobox>
            </v-col>
          </v-row>

          <v-row justify="center" align="center">
            <v-col cols="6">
              <v-text-field label="ราคาซื้อ / หน่วย" type="number" v-model.number="carPartForm.PriceBuy" :rules="rules.PriceBuy"></v-text-field>
            </v-col>

            <v-col cols="6">
              <v-text-field label="ราคาขาย / หน่วย" type="number" v-model.number="carPartForm.PriceSell" :rules="rules.PriceSell"></v-text-field>
            </v-col>
          </v-row>

          <v-row justify="center" align="center">
            <v-col cols="6">
              <v-text-field label="จำนวนชิ้น" type="number" v-model.number="carPartForm.Amount" :rules="rules.Amount"></v-text-field>
            </v-col>

            <v-col cols="6">
              <v-text-field label="รายละเอียดสินค้า" type="text" v-model="carPartForm.Detail"></v-text-field>
            </v-col>
          </v-row>
        </v-card-text>
      </v-form>

      <v-row style="padding-bottom : 40px " justify="center" align="center">
        <v-btn :disabled="!formIsValid" color="grey" large flat @click="saveCarPartCarGeneration">บันทึกข้อมูล</v-btn>
      </v-row>
    </v-card>
  </v-app>
</template>

<script>
import http from "../http-common";
export default {
  name: "CarPart",
  data() {
    const defaultForm = Object.freeze({
      PassOld: "",
      PassNew: "",
      PartNo: "",
      NameItem: "",
      Company: "",
      CarTypeId: "",
      CarLabelId: "",
      CarGenerationId: "",
      PriceBuy: "",
      PriceSell: "",
      Amount: "",
      Detail: ""
    });
    return {
      carPartForm: Object.assign({}, defaultForm),
      rules: {
        PassOld: [value => !!value || "กรุณากรอกรหัสสินค้าเดิม."],
        PassNew: [value => !!value || "กรุณากรอกรหัสสินค้าใหม่."],
        NameItem: [value => !!value || "กรุณากรอกชื่อสินค้า."],
        Company: [value => !!value || "กรุณากรอกชื่อบริษัท."],
        CarTypeId: [value => !!value || "กรุณาเลือกประเภทรถ."],
        CarLabelId: [value => !!value || "กรุณาเลือกยี่ห้อรถ."],
        CarGenerationId: [value => !!value || "กรุณาเลือกรุ่นรถ."],
        PriceBuy: [value => !!value || "กรุณากรอกราคาต้นทุนสินค้า."],
        PriceSell: [value => !!value || "กรุณากรอกราคาขายสินค้า."],
        Amount: [value => !!value || "กรุณากรอกจำนวนสินค้า."]
        
      },
      CarTypes: [],
      CarLabels: [],
      CarGenerations: [], //ประกาศเพื่อรอรับจาก method getCarGenerations
      carPartCarGeneration: {},
      tempCarGenerations: [],
      tempCarLabels: []
    };
  },
  computed: {
      formIsValid () {
        return (
        this.carPartForm.PassOld&&
        this.carPartForm.PassNew&&
        this.carPartForm.NameItem&&
        this.carPartForm.Company&&
        this.carPartForm.CarTypeId&&
        this.carPartForm.CarLabelId&&
        this.carPartForm.CarGenerationId&&
        this.carPartForm.PriceBuy&&
        this.carPartForm.PriceSell&&
        this.carPartForm.Amount    
        )
      },
    },
  methods: {
    resetForm () {
        this.carPartForm = Object.assign({}, this.defaultForm)
        this.$refs.carPartForm.reset()
      },
    getCarTypes() {
      http.get("/cartypes").then(response => {
        console.log(response.data);
        this.CarTypes = response.data;
      });
    },
    getCarLabels() {
      http.get("/carlabels").then(response => {
        console.log(response.data);
        this.CarLabels = response.data;
      });
    },
    getCarGenerations() {
      http.get("/cargenerations").then(response => {
        console.log(response.data);
        this.CarGenerations = response.data;
        this.tempCarGenerations = response.data;
      });
    },
    getPartNo() {
      http
        .get("/getpartno")
        .then(response => {
          this.carPartForm.PartNo = response.data;
        })
        .catch(() => {
          this.carPartForm.PartNo = 1;
        });
    },
    filterCarType() {
      console.log(this.carPartForm.CarLabels);
      let temp = this.CarGenerations;
      if (this.carPartForm.CarLabelId) {
        this.tempCarGenerations = temp.filter(
          cargeneration =>
            cargeneration.carLabel.carLabelId === this.carPartForm.CarLabelId.carLabelId &&
            cargeneration.carType.carTypeId === this.carPartForm.CarTypeId.carTypeId
        );
      } else {
        this.tempCarGenerations = temp.filter(
          cargeneration =>
            cargeneration.carType.carTypeId === this.carPartForm.CarTypeId.carTypeId
        );
      }
      //let temp = this.CarLabels
      //this.tempCarLabels = temp.filter(carlabel => carlabel.)
    },
    filterCarLabel() {
      console.log(this.carPartForm.CarLabelId);
      let temp = this.CarGenerations;
      if (this.carPartForm.CarTypeId) {
        this.tempCarGenerations = temp.filter(
          cargeneration =>
            cargeneration.carLabel.carLabelId === this.carPartForm.CarLabelId.carLabelId &&
            cargeneration.carType.carTypeId === this.carPartForm.CarTypeId.carTypeId
        );
      } else {
        this.tempCarGenerations = temp.filter(
          cargeneration =>
            cargeneration.carLabel.carLabelId === this.carPartForm.CarLabelId.carLabelId
        );
      }
    },
    saveCarPartCarGeneration() {
      http
        .post("/save/carpart", {
          carPart: {
            carPartName: this.carPartForm.NameItem,
            passOld: this.carPartForm.PassOld,
            passNew: this.carPartForm.PassNew,
            priceBuy: this.carPartForm.PriceBuy,
            priceSell: this.carPartForm.PriceSell,
            company: this.carPartForm.Company,
            amount: this.carPartForm.Amount,
            detail: this.carPartForm.Detail
          },
          carGeneration: this.carPartForm.CarGenerationId
        })
        .then(response => {
          alert("เพิ่มข้อมูลสำเร็จ");
          console.log(response.data);
          this.message = "เพิ่มข้อมูลสำเร็จ";
          this.resetForm();

          this.getPartNo();
        })
        .catch(e => {
          console.log(e);
          this.message = "เพิ่มข้อมูลไม่สำเร็จ";
        })
        .finally(() => {
          this.snackbar = !this.snackbar;
          this.reset();
        });
    }
  },
  mounted() {
    this.getCarTypes();
    this.getCarLabels();
    this.getCarGenerations();
    this.getPartNo();
  }
};
</script>
<style  scoped,lang="scss" scoped>
</style>