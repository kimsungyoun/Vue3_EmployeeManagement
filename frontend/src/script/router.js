import Login from "@/pages/Login.vue";
import Home from "@/pages/Home.vue";

import Add from "@/pages/EmployeeAdd.vue";
import Reason from "@/pages/WorkReason.vue";

import employeeList from "@/pages/employee/employeeList";
import employeeView from "@/pages/employee/employeeView";
import employeeModify from "@/pages/employee/employeeModify";

import workList from "@/pages/work/workList";

import admin from "@/pages/admin/adminView";

import {createRouter, createWebHistory} from "vue-router";

const routes = [
    {path: '/', component: Home},
    {path: '/login', component: Login},
    {path: '/employee/add', component: Add},
    {path: `/workReason/:workno`, component: Reason},
    
    {path: '/employeeList', component: employeeList},
    {path: '/employeeView/:empid', component: employeeView},
    {path: '/employeeModify/:empid', component: employeeModify},
    {path: '/workList', component:workList},
    {path: '/admin', component: admin},
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;