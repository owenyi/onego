<template>
  <div id="textArea">
    <!-- contents -->
    <div id="pages">
        <Page />
    </div>
  </div>
</template>

<script>
import Vue from 'vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter)

import Page from '../components/layout/Page.vue'

  export default Vue.extend({
		data: () => ({

		}),
		name: "Header",
		components:{
			Page
		},
	})

</script>
<style>
</style>