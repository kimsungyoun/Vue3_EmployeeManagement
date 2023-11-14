import {createStore} from "vuex"

const store= createStore({
    state(){
        return {
            account: {
                id: 0,
                name: "",
                position:"",
            }
        }
    },
    mutations: {
        setAccount(state, payload){
            state.account.id = payload;
            state.account.position = payload
        }
    }
})

export default store;