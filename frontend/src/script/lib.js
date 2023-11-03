export default {
    formattedTime(val){
        if(val instanceof Date){
            return `${String(val.getFullYear())}-${String(val.getMonth()+1).padStart(2, '0')}-${String(val.getDate()).padStart(2,'0')}`;
        }else{
            const dateObj = new Date(val);
            return `${String(dateObj.getFullYear())}-${String(dateObj.getMonth()+1).padStart(2, '0')}-${String(dateObj.getDate()).padStart(2,'0')}`;
        }

    }
}