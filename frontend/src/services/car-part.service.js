export default class CarPartService {
  constructor() {}

  install(Vue) {
    const root = new Vue({
      data: {
        carPart:[]
      },
    });
    Vue.prototype.$carPartService = root
  }
  
}
