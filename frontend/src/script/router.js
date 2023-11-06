import Work from "@/pages/Work.vue";
import Home from "@/pages/Home.vue";
import Employee from "@/pages/Employee.vue";
import Login from "@/pages/Login.vue";
import Info from "@/pages/EmployeeInfo.vue";
import Add from "@/pages/EmployeeAdd.vue";
import Reason from "@/pages/WorkReason.vue";

import {createRouter, createWebHistory} from "vue-router";

const routes = [
    {path: '/', component: Home},
    {path: '/login', component: Login},
    {path: '/employee', component: Employee},
    {path: '/work', component: Work},
    {path: `/employeeInfo/:empid`,component: Info},
    {path: '/employee/add',component:Add},
    {path: '/workReason',component: Reason},

]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;