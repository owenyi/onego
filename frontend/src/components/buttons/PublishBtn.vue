<template>
	<v-btn
		rounded
		outlined
		color="#00d5aa"
		class="btn"
		@click="publish">
		발행
	</v-btn>
</template>

<script lang="ts">
	import Vue from 'vue'

	export default Vue.extend({
		name:"PublishBtn",
		data: ()=>({
		}),
		methods:{
			publish(){
				this.$emit('publish', true);
			}
		}
	})
</script>

<style lang="sass">

</style>>