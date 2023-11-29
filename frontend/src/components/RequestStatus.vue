<template>
    <select v-model='selectedStatus'>
      <option v-for="i in options" :key="i.value" :value="i.value" > {{i.label}} </option>
    </select>
</template>
  
<script setup>
import { ref, watch, defineProps, defineEmits } from "vue";
  
const props = defineProps({
    value: String
});
  
const selectedStatus = ref(props.value);
  
const options = ref([
    {value:"진행중", label:"진행중"},
    {value:"처리완료", label:"처리완료"},
    {value:"취소", label:"취소"},
]);
  
const emit = defineEmits(["status"]);
  
watch(
    ()=> props.value,
    (newValue)=>{
        selectedStatus.value = newValue;
      emit("status", newValue);    
    }
  )
  </script>