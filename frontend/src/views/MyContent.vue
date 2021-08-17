<template>
  <div id="textArea">
    <!-- contents -->
    <div id="Posts">
        <Mypost />
    </div>
  </div>
</template>

<script>
import Vue from 'vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter)

import Mypost from '../components/layout/Mypost.vue'

  export default Vue.extend({
		data: () => ({
      
		}),
		name: "Header",
		components:{
			Mypost,
		},
	})

</script>
<style>

</style>