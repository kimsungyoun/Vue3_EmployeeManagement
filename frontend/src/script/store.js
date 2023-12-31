import {createStore} from "vuex"

const store= createStore({
    state(){
        return {
            account: {
                no: 0,
                id:"",
                position:"",
            }
        }
    },
    mutations: {
        setAccount(state, payload){
            state.account.no = payload.no;
            state.account.id =payload.id;
            state.account.position = payload.position;
        }
    }
})

export default store;