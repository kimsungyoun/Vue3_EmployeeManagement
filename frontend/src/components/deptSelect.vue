<template>
  <select v-model='selectedDept'>
    <option v-for="i in options" :key="i.value" :value="i.value" > {{i.label}} </option>
  </select>
</template>

<script setup>
import { ref, watch, defineProps, defineEmits } from "vue";
//import { ref, watch, defineProps } from "vue";

const props = defineProps({
  value:String
});

const selectedDept = ref(props.value);

const options = ref([
  { value: "개발팀", label: "개발팀" },
  { value: "인사팀", label: "인사팀" },
  { value: "경영팀", label: "경영팀" },
  { value: "지원팀", label: "지원팀" },
  { value: "영업팀", label: "영업팀" },
]);

const emit = defineEmits(["dept"]);

//watch(selectedDept, (option) => {emit("dept", option);});

watch(
  ()=> props.value,
  (newValue)=>{
    selectedDept.value = newValue;
    emit("dept", newValue);    
  }
)
</script>