<template>
  <select v-model='selectedDept'>
    <option v-for="i in options" :key="i.value" :value="i.value" > {{i.label}} </option>
  </select>
</template>

<script setup>
import { ref, watch, defineProps, defineEmits } from "vue";

const props = defineProps({
  value:String
});

const selectedDept = ref(props.value);

const options = ref([
  { value: "병원", label: "병원" },
  { value: "행사", label: "행사" },
  { value: "개인업무", label: "개인업무" },
  { value: "개인사유", label: "개인사유" },
]);

const emit = defineEmits(["detail"]);

watch(
  ()=> props.value,
  (newValue)=>{
    selectedDept.value = newValue;
    emit("detail", newValue);    
  }
)
</script>