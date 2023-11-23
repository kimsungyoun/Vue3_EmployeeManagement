import Home from "@/pages/Home.vue";
import Notice from "@/pages/notice/noticeList.vue";
import MyPage from "@/pages/myPage";

import employeeAdd from "@/pages/employee/employeeAdd.vue";
import employeeList from "@/pages/employee/employeeList";
import employeeView from "@/pages/employee/employeeView";
import employeeModify from "@/pages/employee/employeeModify";

import workList from "@/pages/work/workList";

import admin from "@/pages/admin/adminView";

import writeRequest from "@/pages/request/writeRequest";

import noticeWrite from "@/pages/notice/noticeWrite";
import noticeDetail from "@/pages/notice/noticeDetail";
import noticeModify from "@/pages/notice/noticeModify";

import {createRouter, createWebHistory} from "vue-router";

const routes = [
    {path: '/', component: Home},
    {path: '/notice', component: Notice},
    {path: '/myPage/:empno', component:MyPage},
    {path: '/employeeAdd', component: employeeAdd},    
    {path: '/employeeList', component: employeeList},
    {path: '/employeeView/:empid', component: employeeView},
    {path: '/employeeModify/:empid', component: employeeModify},
    {path: '/workList', component:workList},
    {path: '/admin', component: admin},
    {path: '/writeRequest', component: writeRequest},
    {path: '/noticeWrite', component: noticeWrite},
    {path: '/noticeDetail/:nno', component:noticeDetail},
    {path: '/noticeModify/:nno', component:noticeModify},
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

export default router;