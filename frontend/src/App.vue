<template>
  <v-app >
	<Header :class="$route.meta.headerClass"/>
	<router-view id="content-margin"/>
	<Footer v-if="$route.meta.showFooter"/>
  </v-app>
</template>

<script lang="ts">
	import Vue from 'vue'
	import Header from '@/components/layout/Header.vue'
	import Footer from '@/components/layout/Footer.vue'
	
	// import { AmplifyEventBus } from 'aws-amplify-vue'

	export default Vue.extend({
		name: "App",
		components:{
			Header, Footer
		},
		created(){
			this.$store.dispatch('findUser');
			// AmplifyEventBus.$on('authStaet', info => {
			// 	if(info === 'signedIn'){
			// 		this.findUser()
			// 	}else{
			// 		this.signedIn = false
			// 	}
			// })
		},
	})
</script>


<style lang ="scss">
	#app {
		font-family: 'Nanum Myeongjo', serif !important;
	}
	#content-margin{
		margin-top:100px;
		margin-bottom:400px;
	}
</style>
