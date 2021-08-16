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
	import http from '@/http/http-common'

	export default Vue.extend({
		name: "App",
		components:{
			Header, Footer
		},
		async created(){
			await this.$store.dispatch('findUser');
			if(Object.keys(this.$store.state.user.userAccount).length != 0){
				await http
					.get('/users/'+this.$store.state.user.userAccount.attributes.email)
					.then(response => {
						this.$store.commit('setUserInfo', response.data);
						console.log(this.$store.state.user.userInfo)
					})
			}
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
