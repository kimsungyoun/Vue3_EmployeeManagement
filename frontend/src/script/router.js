import Home from "@/pages/Home.vue";
import admin from "@/pages/admin/adminView";

import MyPage from "@/pages/employee/myPage";
import employeeAdd from "@/pages/employee/employeeAdd.vue";
import employeeList from "@/pages/employee/employeeList";
import employeeView from "@/pages/employee/employeeView";
import employeeModify from "@/pages/employee/employeeModify";

import workList from "@/pages/work/workList";


import noticeList from "@/pages/notice/noticeList.vue";
import noticeWrite from "@/pages/notice/noticeWrite";
import noticeDetail from "@/pages/notice/noticeDetail";
import noticeModify from "@/pages/notice/noticeModify";

import writeRequest from "@/pages/request/writeRequest";
import requestDetail from "@/pages/request/requestDetail";

import {createRouter, createWebHistory } from "vue-router";

const routes = [
    {path: '/', component: Home},
    {path: '/admin', component: admin},
    {path: '/myPage/:empid', component:MyPage},
    {path: '/employeeAdd', component: employeeAdd},    
    {path: '/employeeList', component: employeeList},
    {path: '/employeeView/:empid', component: employeeView},
    {path: '/employeeModify/:empid', component: employeeModify},
    {path: '/workList', component:workList},
    {path: '/notice', component: noticeList},
    {path: '/noticeWrite', component: noticeWrite},
    {path: '/noticeDetail/:nno', component:noticeDetail},
    {path: '/noticeModify/:nno', component:noticeModify},
    {path: '/writeRequest', component: writeRequest},
    {path: '/requestDetail/:rno', component:requestDetail},
]

const router = createRouter({
    history: createWebHistory(),
    routes
})

router.beforeEach((to, from, next)=> {
    const isLoggedIn = !!sessionStorage.getItem("no");
    if(to.path !== '/' && !isLoggedIn){
        next("/");
    }else{
        next();
    }
});

export default router;