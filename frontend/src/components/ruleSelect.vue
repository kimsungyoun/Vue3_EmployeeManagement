<template>
    <select v-model='selectedRule' >
      <option v-for="i in options" :key="i.value" :value="i.value">
        {{i.label}}
      </option>
    </select>
</template>

<script setup>
import { ref, watch, defineProps} from "vue";

const props = defineProps({
  value:String,
});

const selectedRule = ref(props.value);

const options = ref([
  { value: "인턴", label: "인턴" },
  { value: "사원", label: "사원" },
  { value: "대리", label: "대리" },
  { value: "과장", label: "과장" },
  { value: "부장", label: "부장" },
  { value: "팀장", label: "팀장" },
]);

watch(
  ()=> props.value,
  (newValue)=>{
    selectedRule.value = newValue;
  }
)

</script>