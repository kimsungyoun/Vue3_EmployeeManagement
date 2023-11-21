import {createStore} from "vuex"

const store= createStore({
    state(){
        return {
            account: {
                id: 0,
                position:""
            }
        }
    },
    mutations: {
        setAccount(state, payload){
            state.account.id = payload.id;
            state.account.position = payload.position;
        }
    }
})

export default store;