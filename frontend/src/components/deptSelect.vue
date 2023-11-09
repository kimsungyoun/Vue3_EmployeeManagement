<template>
  <select v-model='selectedOption'>
    <option v-for="i in options" :key="i.value" :value="i.value">
      {{i.label}}
    </option>
  </select>
</template>

<!-- <script>
export default {
  props:{
    value: Object
  },
  data(){
    return{
      selectedOption: this.value,
      options: [
        { value: "개발팀", label: "개발팀" },
        { value: "인사팀", label: "인사팀" },
        { value: "경영팀", label: "경영팀" },
        { value: "지원팀", label: "지원팀" },
        { value: "영업팀", label: "영업팀" },
      ],
    }
  },
  watch:{
    selectedOption(option){
      this.$emit("dept", option);
    }
  }
}
</script> -->

<script setup>
import { ref, watch, defineProps, defineEmits } from "vue";

const props = defineProps({
  value: Object,
});

const selectedOption = ref(props.value);

const options = ref([
  { value: "개발팀", label: "개발팀" },
  { value: "인사팀", label: "인사팀" },
  { value: "경영팀", label: "경영팀" },
  { value: "지원팀", label: "지원팀" },
  { value: "영업팀", label: "영업팀" },
]);

const emit = defineEmits(["dept"]);

watch(selectedOption, (option) => {
  emit("dept", option);
});
</script>