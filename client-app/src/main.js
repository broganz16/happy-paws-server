import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "bootstrap";
import "bootstrap/dist/css/bootstrap.min.css";
import { library } from "@fortawesome/fontawesome-svg-core";
import { faArrowUp } from "@fortawesome/free-solid-svg-icons";
import { faArrowDown } from "@fortawesome/free-solid-svg-icons";
import { faStarHalf } from "@fortawesome/free-solid-svg-icons";
import { faStar } from "@fortawesome/free-solid-svg-icons";
import { faPen } from "@fortawesome/free-solid-svg-icons";
import { faBell } from "@fortawesome/free-solid-svg-icons";
import { faEye } from "@fortawesome/free-solid-svg-icons";
import { faCheck } from "@fortawesome/free-solid-svg-icons";
import { faFileImport } from "@fortawesome/free-solid-svg-icons";
import { FontAwesomeIcon } from './plugins/font-awesome'
import { faFacebook } from '@fortawesome/free-brands-svg-icons'
import { faTwitter } from '@fortawesome/free-brands-svg-icons'
import { faInstagram } from '@fortawesome/free-brands-svg-icons'
import { faTrash } from "@fortawesome/free-solid-svg-icons";
import { faPlus } from "@fortawesome/free-solid-svg-icons";
import { faExclamation } from "@fortawesome/free-solid-svg-icons";
import PrimeVue from 'primevue/config';
import vue3StarRatings from "vue3-star-ratings";
import Notifications from '@kyvg/vue3-notification'
import VueAgile from 'vue-agile'
import Pagination from 'v-pagination-3';
import mitt from 'mitt';


library.add(faCheck);
library.add(faFacebook);
library.add(faTwitter);
library.add(faInstagram);
library.add(faFileImport);
library.add(faBell);
library.add(faEye);
library.add(faTrash);
library.add(faPlus);
library.add(faArrowUp);
library.add(faArrowDown);
library.add(faStarHalf);
library.add(faStar);
library.add(faPen);
library.add(faExclamation);
const emitter = mitt();


const app = createApp(App)
app.use(router)
app.use(Notifications)
app.use(VueAgile)
app.use(store)
app.use(PrimeVue)
app.component("font-awesome-icon", FontAwesomeIcon)
app.component("vue3-star-ratings", vue3StarRatings)
app.component('pagination', Pagination)
app.config.globalProperties.emitter = emitter
app.mount('#app')