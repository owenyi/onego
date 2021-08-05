<template>
  <div>
	<v-flex id="myprof-margin" class="mx-auto">
	  <div align="center" class="spacing">
		<div class="image-container">
			<img src="@/assets/images/beach.jpg" width="100%" height="auto">
			<v-list-item-avatar id="prof-pic" absolute elevation="4" size=100>
				<img :src="user.pic">
			</v-list-item-avatar>
			<v-btn id="myprof-write-btn" href="/write" absolute rounded outlined color="#00d5aa">
				글쓰기
			</v-btn>
			<v-menu bottom left offset-y content-class="elevation-0">
				<template v-slot:activator="{ on, attrs }">
					<v-btn icon absolute
						v-bind="attrs"
						v-on="on"
						id="dots-btn">
						<v-icon style="opacity:60%;">mdi-dots-vertical</v-icon>
					</v-btn>
				</template>
				<div style="margin-right:17px;">
				<a class="link-hover" id="edit-hover" href="/edit-prof">
				<v-list dense outlined class="mt-2 pt-0 pb-0 pl-3 pr-3">
					<v-list-item>
					  <v-list-item-title>
					  	<label class="label" style="font-size:0.95em;opacity:60%;">프로필 수정</label>
					  </v-list-item-title>
					</v-list-item>
				</v-list>
				</a>
				</div>
			</v-menu>

		</div>
	  </div>
	  <div class="spacing">
		  <div><label style="font-size:1.4em;font-weight:bold;">{{user.nickname}}</label></div>
		  <div><label style="font-size:0.95em;opacity:75%;">{{user.intro}}</label></div>
	  </div>
	  <div class="spacing">
	    <div class="follow">
		  <a :href="disabledFollower ? 'javascript:void(0)' : '#'" id="follower-hover" class="link-hover">
			<div>
		    <div><label class="label label-follow" style="font-size:0.80em;opacity:60%;">구독자</label></div>
		    <div><label class="label label-number" style="font-size:1.2em;opacity:55%;">{{user.followers}}</label></div>
			</div>
		  </a>
		</div>
		<div class="follow" style="margin-left:50px;">
		  <a :href="disabledFollowing ? 'javascript:void(0)' : '/userprofile'" id="following-hover" class="link-hover">
			<div>
		    <div><label class="label label-follow" style="font-size:0.80em;opacity:60%;">관심 작가</label></div>
		    <div><label class="label label-number" style="font-size:1.2em;opacity:55%;">{{user.following}}</label></div>
			</div>
		  </a>
	    </div>
	  </div>
	  <div style="margin-top:150px;padding-top:25px;padding-bottom:10px;background-color:#FAFAFA;">
	  	<p style="opacity:55%;margin-left:30px;font-size:1.1em;"><em><strong>커서가 깜빡이는 순간, 당신은 이미 작가입니다.</strong></em></p>
	 	<p style="opacity:55%;margin-left:30px;font-size:1.1em;"><em><strong>ONEGO에서는 작가님을 응원합니다.</strong></em></p>
	  </div>
	</v-flex>
  </div>
</template>

<script lang="ts">
	import Vue from 'vue'

	export default Vue.extend({
		data: () => ({
			user:{
				name:'Mary Jane', nickname:'Mary', email:'mj123@gmail.com',
				intro:'Hello, I am an avid writer',
				pic:"https://randomuser.me/api/portraits/women/82.jpg",
				followers:'3', following:'5'
			},
			disabledFollowing:false,
			disabledFollower:false
		}),
		created(){
			if(this.user.following == '0'){
				this.disabledFollowing = true;
			}
			if(this.user.followers == '0'){
				this.disabledFollower = true;
			}
			//console.log(this.disabledFollowing);
			//console.log(this.disabledFollower);
		}
	})
</script>

<style>
.v-application .myprof-header-color .white{
	background-color:transparent !important;
}
#myprof-margin{
	max-width:55%;
	margin-top: -100px;
}
.spacing{
	margin-bottom:30px;
}
.link-hover{
	text-decoration: none !important;
	color: black !important;
}
#myprof-write-btn:hover{
	color:#02bf99 !important;
}
.image-container{
	position:relative;
}
.image-container img{
	display:block;
}
.image-container #prof-pic{
	position:absolute;
	bottom:-55px;
	right:20px;
}
.image-container #myprof-write-btn{
	position:absolute;
	bottom:-150px;
	right:20px;
}
.image-container #dots-btn{
	position:absolute;
	bottom:-150px;
	right:-14px;
}
.follow{
	float:left;
}
#edit-hover:hover .label{
	color:#00d5aa !important;
	font-weight:900;
	opacity:120%;
}
#edit-hover:hover .v-list{
	background-color:#FAFAFA !important;
}
.link-hover:hover .label{
	cursor:pointer !important;
}
.link-hover:hover .label-follow{
	color:black !important;
}
.link-hover:hover .label-number{
	text-decoration:underline !important;
	color:#00d5aa !important;
}
</style>