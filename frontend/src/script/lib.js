export default {
    formattedTime(val){
        if(val instanceof Date){
            return `${String(val.getFullYear())}-${String(val.getMonth()+1).padStart(2, '0')}-${String(val.getDate()).padStart(2,'0')}`;
        }else{
            const dateObj = new Date(val);
            return `${String(dateObj.getFullYear())}-${String(dateObj.getMonth()+1).padStart(2, '0')}-${String(dateObj.getDate()).padStart(2,'0')}`;
        }
    },
    formattedTime2(val){
        if(val instanceof Date){
            return `${String(val.getFullYear())}년 ${String(val.getMonth()+1).padStart(2, '0')}월 ${String(val.getDate()).padStart(2,'0')}일`;
        }else{
            const dateObj = new Date(val);
            return `${String(dateObj.getFullYear())}년 ${String(dateObj.getMonth()+1).padStart(2, '0')}월 ${String(dateObj.getDate()).padStart(2,'0')}일`;
        }
    }
}