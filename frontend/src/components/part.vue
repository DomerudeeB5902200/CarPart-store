<template>
  <v-app>
    <v-card class="mx-auto" width="95%">
      <v-toolbar color="blue-grey" dark flat>
        <v-toolbar-title
          style="width:100%; text-align:center; color:white ; front-size:35px"
        >ค้นหาสินค้า</v-toolbar-title>
      </v-toolbar>
      <v-row justify="center" align="center">
        <v-col cols="4">
          <v-text-field
            outlined
            dense
            v-model="carPartPassOld"
            v-on:keydown="searchCarPart"
            label="ค้นหาด้วย รหัสเดิม"
            append-icon="search"
          ></v-text-field>
        </v-col>
      </v-row>
      <v-data-table
        :headers="headers"
        :items="tempcarPartCarGeneration"
        :items-per-page="5"
        class="elevation-1"
      >
        <template v-slot:item.insert="{ item }">
          <v-icon
            :disabled="item.carPart.amount<=0"
            @click="dialog = true , tempCarPart = item , setMaxAmount()"
          >add_shopping_cart</v-icon>
        </template>
        <template v-slot:item.editItem="{ item }">
          <v-icon @click="editcarpartdialog = true , tempCarPart = item , editCarPart()">edit</v-icon>
        </template>
      </v-data-table>
    </v-card>

    <v-dialog v-model="editcarpartdialog">
      <v-card class="mx-auto" width="100%">
        <v-toolbar color="indigo accent-1" dark flat>
          <v-toolbar-title>แก้ไขรายการอะไหล่</v-toolbar-title>
        </v-toolbar>
        <v-form ref="editCarPartForm">

        <v-card-text>
          <v-row justify="center" align="center">
            <v-col cols="6">
              <v-text-field outlined dense label="รหัสเดิม" type="text" v-model="editCarPartForm.PassOld" :rules="rules.PassOld"></v-text-field>
            </v-col>

            <v-col cols="6">
              <v-text-field outlined dense label="รหัสใหม่" type="text" v-model="editCarPartForm.PassNew" :rules="rules.PassNew"></v-text-field>
            </v-col>
          </v-row>

          <v-row justify="center" align="center">
            <v-col cols="6">
              <v-text-field label="ชื่อสินค้า" type="text" v-model="editCarPartForm.NameItem" :rules="rules.NameItem"></v-text-field>
            </v-col>

            <v-col cols="6">
              <v-text-field label="บริษัทที่สั่งซื้อ" type="text" v-model="editCarPartForm.Company" :rules="rules.Company"></v-text-field>
            </v-col>
          </v-row>

          <v-row justify="center" align="center">
            <v-col cols="4">
              <v-combobox
                v-model="editCarPartForm.CarTypeId"
                :rules="rules.CarTypeId"
                :items="CarTypes"
                item-text="carTypeName"
                item-value="carTypeId"
                label="ประเภทรถ"
              ></v-combobox>
            </v-col>

            <v-col cols="4">
              <v-combobox
                v-model="editCarPartForm.CarLabelId"
                :rules="rules.CarLabelId"
                :items="CarLabels"
                item-text="carLabelName"
                item-value="carLabelId"
                label="ยี่ห้อรถ"
              ></v-combobox>
            </v-col>

            <v-col cols="4">
              <v-combobox
                v-model="editCarPartForm.CarGenerationId"
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
              <v-text-field label="ราคาซื้อ / หน่วย" type="number" v-model.number="editCarPartForm.PriceBuy" :rules="rules.PriceBuy"></v-text-field>
            </v-col>

            <v-col cols="6">
              <v-text-field label="ราคาขาย / หน่วย" type="number" v-model.number="editCarPartForm.PriceSell" :rules="rules.PriceSell"></v-text-field>
            </v-col>
          </v-row>

          <v-row justify="center" align="center">
            <v-col cols="6">
              <v-text-field label="จำนวนชิ้น" type="number" min="1" v-model.number="editCarPartForm.Amount" :rules="rules.Amount"></v-text-field>
            </v-col>

            <v-col cols="6">
              <v-text-field label="รายละเอียดสินค้า" type="text" v-model="editCarPartForm.Detail"></v-text-field>
            </v-col>
          </v-row>
        </v-card-text>
        </v-form>

        <v-card-actions>
          <v-spacer></v-spacer>
          <!--<v-btn color="blue darken-1" text @click="editcarpartdialog = false">Close</v-btn>-->
          <v-btn color="blue darken-1" dark flat @click="editcarpartdialog = false">Close</v-btn>
          <v-btn :disabled="!formIsValid" color="blue darken-1" flat @click="saveCarPartCarGeneration">Save</v-btn>
          <!--<v-btn color="blue darken-1" text @click="saveCarPartCarGeneration">Save</v-btn>-->
        </v-card-actions>
      </v-card>
    </v-dialog>
    <v-row justify="center">
      <v-dialog v-model="dialog" persistent max-width="300px">
        <v-card>
          <v-card-title>
            <!--<v-toolbar color="indigo accent-1" dark flat>
          <v-toolbar-title>แก้ไขรายการอะไหล่</v-toolbar-title>
            </v-toolbar>-->
            <v-toolbar color="indigo accent-1" dark flat>
            <span class="headline">กรอกจำนวนสินค้า</span>
            </v-toolbar>
          </v-card-title>
          <v-card-text>
            <v-text-field
              v-model.number="amount"
              label="จำนวนชิ้น"
              type="number"
              min="1"
              :max="maxAmount"
              required
            ></v-text-field>
          </v-card-text>
          <v-card-actions>
            <v-spacer></v-spacer>
            <!--<v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>-->
            <v-btn color="blue darken-1" dark flat @click="dialog = false">Close</v-btn>
            <v-btn color="blue darken-1" dark flat @click="addCarPart(item)">Save</v-btn>
            <!--<v-btn color="blue darken-1" text @click="addCarPart(item)">Save</v-btn>-->
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-row>
  </v-app>
</template>

<script>
import http from "../http-common";
export default {
  data () {
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
      editCarPartForm: Object.assign({}, defaultForm),
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
    tempCarGenerations: [],

    
    maxAmount: 1,
    dialog: false,
    amount: 1,
    tempCarPart: {
      carPart: {
        amount: 1
      }
    
    },
    carPartPassOld: "",
    tempcarPartCarGeneration: [],
    editcarpartdialog: false,
    /*carparts: [
      {
        PassOld: "B5902200",
        PassNew: "C5902200",
        PartNo: "5902200",
        NameItem: "สายพาน",
        CarLabel: "Honda",
        CarGeneration: "Accord G8",
        Company: "ต้า มหาชน",
        PriceBuy: "200",
        PriceSell: "450",
        Amount: "10",
        Detail: "ขนาด 30 เซนติเมตร"
      }
    ],*/
    CarPartCarGenerations: [], //ได้ของมาจาก Backend แล้ว  getCarPartCarGenerations()
    headers: [
      {
        text: "รหัสเดิม",
        value: "carPart.passOld"
      },
      {
        text: "รหัสใหม่",
        value: "carPart.passNew"
      },
      {
        text: "Part No.",
        value: "carPart.idPartNo"
      },
      {
        text: "ชื่อสินค้า",
        value: "carPart.carPartName"
      },
      {
        text: "ยี่ห้อรถ",
        value: "carGeneration.carLabel.carLabelName"
      },
      {
        text: "รุ่นรถ",
        value: "carGeneration.generationCarName"
      },
      {
        text: "บริษัท",
        value: "carPart.company"
      },
      {
        text: "ราคาซื้อ",
        value: "carPart.priceBuy"
      },
      {
        text: "ราคาขาย",
        value: "carPart.priceSell"
      },
      {
        text: "จำนวน",
        value: "carPart.amount"
      },
      {
        text: "รายละเอียด",
        value: "carPart.detail"
      },
      {
        text: "เพิ่มลงตะกร้า",
        value: "insert",
        align: "center"
      },
      {
        text: "แก้ไขรายระเอียดสินค้า",
        value: "editItem",
        align: "center"
      }
    ]
  }
  },
   computed: {
      formIsValid () {
        return (
        this.editCarPartForm.PassOld&&
        this.editCarPartForm.PassNew&&
        this.editCarPartForm.NameItem&&
        this.editCarPartForm.Company&&
        this.editCarPartForm.CarTypeId&&
        this.editCarPartForm.CarLabelId&&
        this.editCarPartForm.CarGenerationId&&
        this.editCarPartForm.PriceBuy&&
        this.editCarPartForm.PriceSell&&
        this.editCarPartForm.Amount    
        )
      },
    },
  methods: {
    getCarPartCarGenerations() {
      http.get("/carpartcargenerations").then(response => {
        console.log(response.data);
        this.tempcarPartCarGeneration = response.data;
        this.CarPartCarGenerations = response.data;
      });
    },
    addCarPart() {
      let index = this.$carPartService.carPart.findIndex(
        carPart =>
          carPart.carPartCarGenerationId ===
          this.tempCarPart.carPartCarGenerationId
      );
      if (index === undefined || index === null || index === -1) {
        Object.assign(this.tempCarPart, { amount: Number(this.amount) });
        this.$carPartService.carPart.push(this.tempCarPart);
      } else {
        this.$carPartService.carPart[index].amount += Number(this.amount);
      }

      console.log(this.$carPartService.carPart);
      this.dialog = false;
      this.tempCarPart = null;
      this.amount = 1;
    },
    editCarPart() {
      this.getCarTypes();
      this.getCarLabels();
      this.getCarGenerations();

      console.log(this.tempCarPart);
      this.editCarPartForm.PassOld = this.tempCarPart.carPart.passOld;
      this.editCarPartForm.PassNew = this.tempCarPart.carPart.passNew;
      this.editCarPartForm.NameItem = this.tempCarPart.carPart.carPartName;
      this.editCarPartForm.Company = this.tempCarPart.carPart.company;
      this.editCarPartForm.PriceBuy = this.tempCarPart.carPart.priceBuy;
      this.editCarPartForm.PriceSell = this.tempCarPart.carPart.priceSell;
      this.editCarPartForm.Amount = this.tempCarPart.carPart.amount;
      this.editCarPartForm.Detail = this.tempCarPart.carPart.detail;
    },
    getCarTypes() {
      http.get("/cartypes").then(response => {
        console.log(response.data);
        this.CarTypes = response.data;
        this.editCarPartForm.CarTypeId = this.tempCarPart.carGeneration.carType;
      });
    },
    getCarLabels() {
      http.get("/carlabels").then(response => {
        console.log(response.data);
        this.CarLabels = response.data;
        this.editCarPartForm.CarLabelId = this.tempCarPart.carGeneration.carLabel;
      });
    },
    getCarGenerations() {
      http.get("/cargenerations").then(response => {
        console.log(response.data);
        this.CarGenerations = response.data;
        this.tempCarGenerations = response.data;
        this.editCarPartForm.CarGenerationId = this.tempCarPart.carGeneration;
      });
    },
    setMaxAmount() {
      this.maxAmount = this.tempCarPart.carPart.amount;
    },
    saveCarPartCarGeneration() {
      http
        .post("/edit/carpart", {
          carPart: {
            idPartNo: this.tempCarPart.carPart.idPartNo,
            carPartName: this.editCarPartForm.NameItem,
            passOld: this.editCarPartForm.PassOld,
            passNew: this.editCarPartForm.PassNew,
            priceBuy: this.editCarPartForm.PriceBuy,
            priceSell: this.editCarPartForm.PriceSell,
            company: this.editCarPartForm.Company,
            amount: this.editCarPartForm.Amount,
            detail: this.editCarPartForm.Detail
          },
          carGeneration: this.editCarPartForm.CarGenerationId,
          carPartCarGenerationId: this.tempCarPart.carPartCarGenerationId
        })
        .then(response => {
          if (this.$carPartService.carPart.length) {
            let index = this.$carPartService.carPart.findIndex(
              item =>
                item.carPart.idPartNo === this.tempCarPart.carPart.idPartNo
            );
            if (index >= 0) {
              this.$carPartService.carPart[index]["carPart"] = {
                idPartNo: this.tempCarPart.carPart.idPartNo,
                carPartName: this.editCarPartForm.NameItem,
                passOld: this.editCarPartForm.PassOld,
                passNew: this.editCarPartForm.PassNew,
                priceBuy: this.editCarPartForm.PriceBuy,
                priceSell: this.editCarPartForm.PriceSell,
                company: this.editCarPartForm.Company,
                amount: this.editCarPartForm.Amount,
                detail: this.editCarPartForm.Detail
              };
              this.$carPartService.carPart[index][
                "carGeneration"
              ] = this.editCarPartForm.CarGenerationId;
              this.$carPartService.carPart[index][
                "carPartCarGenerationId"
              ] = this.tempCarPart.carPartCarGenerationId;
            }
          }
          alert("แก้ไขข้อมูลสำเร็จ");
          this.editcarpartdialog=false;
          console.log(response.data);
          this.message = "แก้ไขข้อมูลสำเร็จ";
        })
        .catch(e => {
          console.log(e);
          this.message = "แก้ไขข้อมูลไม่สำเร็จ";
        })
        .finally(() => {
          this.snackbar = !this.snackbar;
          this.reset();
        });
    },
    searchCarPart() {
      if (this.carPartPassOld === "") {
        this.tempcarPartCarGeneration = this.CarPartCarGenerations;
      } else {
        let temp = this.CarPartCarGenerations;
        this.tempcarPartCarGeneration = temp.filter(carpartcargeneration =>
          carpartcargeneration.carPart.passOld.includes(this.carPartPassOld)
        );
      }
    }
  },
  mounted() {
    this.getCarPartCarGenerations(); //เรียกฟังห์ชั่นเพื่อมาใช้งาน
  }
};
</script>