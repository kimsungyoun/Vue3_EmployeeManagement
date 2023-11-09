<template>
    <select v-model='selectedOption'>
      <option v-for="i in options" :key="i.value" :value="i.value">
        {{i.label}}
      </option>
    </select>
</template>

<script setup>
import { ref, watch, defineProps, defineEmits } from "vue";

const props = defineProps({
  value: String,
});

const selectedOption = ref(props.value);

const options = ref([
  { value: "인턴", label: "인턴" },
  { value: "사원", label: "사원" },
  { value: "대리", label: "대리" },
  { value: "과장", label: "과장" },
  { value: "부장", label: "부장" },
  { value: "팀장", label: "팀장" },
]);

const emit = defineEmits(["rule"]);

watch(selectedOption, (option) => {
  emit("rule", option);
});
</script>