import Vue from "vue";
import VueRouter from "vue-router";
import UserList from "../views/UserList.vue";
import PatientList from "../views/PatientList.vue";
import ConsultationList from "../views/ConsultationList.vue";
import { auth as doctor_appointments } from "../store/auth.module";
import Login from "../views/Login";
import Admin from "../views/ConsultationList";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Login",
    component: Login,
  },
  {
    path: "/users",
    name: "Users",
    component: UserList,
    beforeEnter: (to, from, next) => {
      if (doctor_appointments.getters.isAdmin) {
        next();
      } else {
        next({ name: "Patients" });
      }
    },
  },
    {
        path: "/consultations",
        name: "Consultations",
        component: ConsultationList,
        beforeEnter: (to, from, next) => {
            if (doctor_appointments.getters.isDoctor) {
                next();
            } else {
                next({ name: "Patients" });
            }
        },
    },
    {
        path: "/consultationSecretary",
        name: "Consultations",
        component: ConsultationList,
        beforeEnter: (to, from, next) => {
            if (doctor_appointments.getters.isSecretary) {
                next();
            } else {
                next({ name: "Patients" });
            }
        },
    },
  {
    path: "/patients",
    name: "Patients",
    component: PatientList,
    beforeEnter: (to, from, next) => {
      if (doctor_appointments.state.status.loggedIn) {
        next();
      } else {
        next({ name: "Home" });
      }
    },
  }, 
    {
    path: "/admin",
    name: "Admin",
    component: Admin,
    beforeEnter: (to, from, next) => {
    if (doctor_appointments.state.status.loggedIn) {
        next();
    } else {
        next({ name: "Home" });
    }
    },
    },

  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue"),
  },
];

const router = new VueRouter({
  routes,
});

export default router;
