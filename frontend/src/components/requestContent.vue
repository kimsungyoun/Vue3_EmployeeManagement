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
  { value: "연차", label: "연차" },
  { value: "오전연차", label: "오전연차" },
  { value: "오후연차", label: "오후연차" },
  { value: "외근", label: "외근" },
]);

const emit = defineEmits(["content"]);

watch(
  ()=> props.value,
  (newValue)=>{
    selectedDept.value = newValue;
    emit("content", newValue);    
  }
)
</script>