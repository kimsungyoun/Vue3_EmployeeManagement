import Login from "@/pages/Login.vue";
import Home from "@/pages/Home.vue";

import employeeAdd from "@/pages/employee/employeeAdd.vue";
import employeeList from "@/pages/employee/employeeList";
import employeeView from "@/pages/employee/employeeView";
import employeeModify from "@/pages/employee/employeeModify";

import workList from "@/pages/work/workList";

import admin from "@/pages/admin/adminView";

import writeRequest from "@/pages/request/writeRequest"

import {createRouter, createWebHistory} from "vue-router";

const routes = [
    {path: '/', component: Home},
    {path: '/login', component: Login},
    {path: '/employeeAdd', component: employeeAdd},    
    {path: '/employeeList', component: employeeList},
    {path: '/employeeView/:empid', component: employeeView},
    {path: '/employeeModify/:empid', component: employeeModify},
    {path: '/workList', component:workList},
    {path: '/admin', component: admin},
    {path: '/writeRequest', component: writeRequest},
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;