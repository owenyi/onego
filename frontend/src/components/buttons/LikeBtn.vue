<template>
    <v-container
    fluid
    class="pa-0"
    >
    <v-btn
        icon
        color="#00d5aa"
        @click="like"
    >
        <span style="font-size: 1.8em;"><i class="fa fa-heart"></i></span>
    </v-btn>
    </v-container>
</template>


<script lang="ts">
import Vue from 'vue'

export default Vue.extend({
    name:"LikeBtn",
    data: ()=>({
    }),
    methods:{
        like(){
            this.$emit('like', true);
        }
    }

})
</script>>

<style scoped>



</style>

