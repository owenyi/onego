<template>
    <div id="profile">
        <div class="profile-text">
            <span class="profile-img">
                <div class="profile-avatar">
                    <a href="/myprofile"><img class="img" :src="user.profileImage"></a>
                </div>
            </span>
            <a href="/myprofile" style="height: 25px; color: #555555; text-decoration: none; vertical-align: middle; float:left;">
                <span class="profile-name">
                    {{ user.nickName }}
                </span>
            </a>
            <div style="margin-top:5px; margin-bottom:14px; padding-top:20px; vertical-align: middle;"><v-btn
                outlined
                rounded
                text
                width="60"
                height="28"
                >
                구독
            </v-btn>
            </div>
            <div class="profile-text-description">
                {{ user.email }}
            </div>
            <div class="profile-text-info">
                {{ user.intro }}
            </div>
        </div>
    </div>   
</template>

<script lang="ts">
import Vue from 'vue'
import http from '../../http/http-common'

declare interface User{
    nickName:string,
    email:string,
	intro:string,
	profileImage:string
}

export default Vue.extend({
    props: {
        id: String
    },
    data: () => ({
        user: {} as User,
    }),
    methods: {
        getUserInfo(id : string){//userEmail을 넣어야하는거 아닌가요? this.$store.state.user.userAccount.attributes.email]
            console.log(id)
            http
            .get('/users/'+id)
            .then(response => {
                console.log(response.data)
                this.user=response.data
            })
        },
    },
    watch: {
        id(){
            console.log(this.id)
            this.getUserInfo(this.id)
        }
    },
})
</script>

<style>
#profile{
    margin: auto;
    max-width:50% !important;
    box-shadow: 3px 3px 8px lightgray;
}
.profile-text{
    padding-left: 25px;
    padding-top: 10px;
    font-family: Noto Sans KR;
}
.profile-name{
    font-size : 1.5rem; 
    font-weight: 500; 
    padding-right: 10px;
}
.profile-link{
    color: #555555;
    text-decoration: none;
}
.profile-text-description{
    font-size : 1.1rem; 
    font-weight: 300; 
    color: #555555;
    /* 글자수 제한*/
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 1; 
    -webkit-box-orient: vertical;
    word-wrap:break-word; 
    height: 1.7em; /*height는 1.7em * 3줄 = 5.1em */
  }

.profile-text-info{
    color: #555555;
    font-size : 1.1rem; 
    line-height: 1.7em; 
    font-weight: 300; 
    letter-spacing: 0;
    /* 글자수 제한*/
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 1; 
    -webkit-box-orient: vertical;
    word-wrap:break-word; 
    height: 5.1em; /*height는 1.7em * 1줄 = 5.1em */
}
.profile-img{
  float: right;
  margin-right: 30px;
  margin-bottom: 5px;
}
.profile-avatar{
  width: 150px;
  height: 150px; 
  border-radius: 70%;
  overflow: hidden;
}
.img{
  width: 100%;
  height: 100%;
  object-fit: cover;
}
</style>
