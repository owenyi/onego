<template>
	<div>
		<v-flex id="editprof-margin" class="mx-auto">
			<div align="center" class="spacing">
				<h2 id="title">프로필 수정</h2>
			</div>
			<!-- 프로필 이미지 파일 선택 -->
			<div align="right">
				<v-list-item-avatar size=90>
					<img :src="user.profileImg">
				</v-list-item-avatar>
				<v-btn 
					id="profileImg"
					fab
					elevation="4"
					small
					absolute
					@click="selectImg"
					type="file"
					ref="profileImage"
					accept=".jpeg, .jpg, .png"
					>
					<v-icon>mdi-camera</v-icon>
				</v-btn>
				<div style="display:none;">
					<input
						id="fileUpload"
						type="file"
						accept="image/*"
						@change="onFileChanged"
					>
				</div>
			</div>
			<!-- 이메일 -->
			<div class="spacing">
				<div style="margin-bottom:8px;">
					<label class="subtitle">이메일</label>
				</div>
				<v-text-field id="name-input" class="name-input"
					:value="user.email"
					:rules="[rules.fieldLimit,rules.required]"
					color="#00d5aa"
					dense
					counter
					maxlength="25"
					single-line
					flat solo
					autofocus 
					disabled/>
			</div>
			<v-divider class="divider-edit"/>
			<!-- 닉네임 수정 -->
			<div class="spacing">
				<div style="margin-bottom:8px;">
					<label class="subtitle">작가 닉네임</label>
				</div>
				<v-text-field id="nickname-input" class="nickname-input"
					v-model="user.nickname"
					:rules="[rules.fieldLimit,rules.required]"
					color="#00d5aa"
					dense
					counter
					maxlength="25"
					single-line
					flat solo />
			</div>
			<v-divider class="divider-edit"/>
			<!-- 소개글 수정 -->
			<div class="spacing">
				<div style="margin-bottom:8px;">
					<label class="subtitle">소개</label>
				</div>
				<v-container fluid>
					<v-textarea
						id="text-area"
						placeholder="간단한 소개를 입력해주세요."
						color="#00d5aa"
						counter
						maxlength="100"
						solo
						auto-grow
						row-height="12px"
						v-model="user.intro"
						:rules="[rules.areaLimit]"/>
				</v-container>
			</div>
			<v-divider class="divider-edit"/>
			<!-- cancel and save buttons -->
			<div align="center">
				<v-btn id="cancel-btn"
					rounded
					outlined
					color="grey"
					@click="$router.go(-1)">
					취소
				</v-btn>&nbsp;&nbsp;
				<v-btn id="save-btn"
					rounded
					outlined
					color="rgb(0, 213, 170)"
					@click="updateProfile">
					저장
				</v-btn>
			</div>
		</v-flex>
	</div>
</template>

<script>
	import Vue from 'vue'
	import http from '../http/http-common'
	import { Auth } from 'aws-amplify'

	export default Vue.extend({
		name: "EditProfile",
		data: () => ({
			user:{
				nickname:'',
				email:'',
				intro:'',
				profileImg: 'null',
				profileImgFile: null,
				errored: false,
				loading: true
			},
			rules: {
				fieldLimit: v => v.length <= 25 || 'Max 25 characters',
				areaLimit: v => v.length <= 100 || 'Max 100 characters',
				required: v => !!v || '필수 입력사항입니다.'
			},
		}),
		methods:{
			async updateProfile(){
				// form data for updating userProfile
				const frm = new FormData();
				frm.append('nickName', this.user.nickname);
				frm.append('profileImageFile', this.user.profileImgFile);
				frm.append('intro', this.user.intro);
				
				// updating cognito
				const user = await Auth.currentAuthenticatedUser();
				await Auth.updateUserAttributes(user, {
					'nickname': this.user.nickname
				});

				// updating user table
				await http
					.put('/users/'+this.user.email, frm)
					.then(response => {
							console.log(response)
					})
					.catch(error => {
						this.errored = true
					} )
					.finally(() => {
						this.loading = false
            	  })    
				
				// 화면 새로고침
				this.$router.go()
			},
			selectImg(){
				document.getElementById("fileUpload").click();
			},
			onFileChanged(e){
				let file = e.target.files[0];
				this.user.profileImgFile = file
				const url = URL.createObjectURL(file)
				this.user.profileImg = url
			}
		},
		async created(){
			if(Object.keys(this.$store.state.user.userAccount).length != 0){
				await http
					.get('/users/'+this.$store.state.user.userAccount.attributes.email)
					.then(response => {
						this.$store.commit('setUserInfo', response.data);
					})
			}
			this.user.nickname = this.$store.state.user.userAccount.attributes.nickname
			this.user.email = this.$store.state.user.userAccount.attributes.email
			this.user.intro = this.$store.state.user.userInfo.intro
			this.user.profileImg = this.$store.state.user.userInfo.profileImage
		},
	})
</script>

<style>
#editprof-margin{
	max-width:55%;
	margin-top: -25px;
}
.spacing{
	margin-bottom:30px;
}
.subtitle{
	color:grey;
	font-size:0.75em;
}
.divider-edit{
	margin-top:-35px;
	margin-bottom:30px;
}
#name-input,
#nickname-input{
	font-size:1.1em !important;
}

#cancel-btn:hover{
	color:#757575 !important;
}
#save-btn:hover{
	color:#02bf99 !important;
}
#profileImg{
	margin-top:105px;
	margin-left:-115px;
}
.name-input .v-input__slot,
.nickname-input .v-input__slot{
	margin-bottom:0 !important;
}
</style>