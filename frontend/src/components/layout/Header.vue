<template>
	<v-card class="header-padding">
		<!-- navbar -->
		<v-app-bar
		fixed
		color="white"
		elevate-on-scroll
		app
		>
		<v-app-bar-nav-icon @click="drawer = true; openMenu()"></v-app-bar-nav-icon>

		<a href="/"><img src="@/assets/logo/onego_logo.jpeg"
			width="120px" height="43.2px"
			style="vertical-align:middle;"></a>

		<v-spacer></v-spacer>
		<!-- write page -->
		<div v-if="$route.meta.showHeader == false">
			<preview-btn/>
			<delete-btn/>
			<save-btn/>
			<post-btn/>
		</div>
		<!-- other pages -->
		<div v-else>
			<div v-if="this.$store.state.user.signedIn==true"><logout-btn /></div>
			<div v-else><signup-btn /><login-btn @login="isAuth = $event" /></div>
		</div>
		<search-modal v-if="$route.meta.showHeader" @openDrawer="drawer = $event" />
		</v-app-bar>

		<!-- sidebar -->
		<v-navigation-drawer
			v-model="drawer"
			temporary
			fixed
			fluid style="height: 100vh; width: 250px;"
			app
			>
			<br><br>
			<div v-if="this.$store.state.user.signedIn==true">
				<div class="side-itms">
					<a href="/myprofile">
					<v-list-item-avatar size=90 class="mx-auto">
						<img :src="$store.state.user.userInfo.profileImage">
					</v-list-item-avatar>
					</a>
				</div>
				<div class="side-itms">
					<v-list-item-content>
						<v-list-item-title>{{this.$store.state.user.userAccount.attributes.nickname}}</v-list-item-title>
					</v-list-item-content>
				</div>
			</div>
			<div v-else>
				<div class="side-itms">
					<v-list-item-avatar size=90 class="mx-auto">
						<img src="@/assets/logo/onego_mac.png">
					</v-list-item-avatar>
				</div>
				<div class="side-itms" >
					<v-list-item-content>
						<em>Finish your sentence</em>
						<em>with ONEGO...</em>
					</v-list-item-content>
				</div>
			</div>
			<br>
			<div class="side-itms">
				<div v-if="this.$store.state.user.signedIn==true">
					<v-btn id="write-btn" href="/write" rounded outlined color="#00d5aa">
						글쓰기
					</v-btn>
				</div>
			<br>
			<v-divider></v-divider>
			<br>
				<v-btn id="menu-btn" rounded outlined color="#00d5aa" @click="openMenu" >
					메뉴
				</v-btn>&nbsp;&nbsp;
				<v-btn id="cate-btn" rounded outlined @click="openCategory">
					카테고리
				</v-btn>
			</div>
			<br>
			<v-divider></v-divider>

			<v-list dense v-if="showMenu">
				<div class="side-itms">
					<v-list-item
					v-for="item in menusBL"
					:key="item.title">
						<v-list-item-content>
							<v-btn text :href="item.link"><v-list-item-title>{{ item.title }}</v-list-item-title></v-btn>
						</v-list-item-content>
					</v-list-item>
				</div>
				<br>
				<div v-if="this.$store.state.user.signedIn==true" class="side-itms">
					<v-list-item
					v-for="item in menusAL"
					:key="item.title">
						<v-list-item-content>
							<v-btn text :href="item.link"><v-list-item-title>{{ item.title }}</v-list-item-title></v-btn>
						</v-list-item-content>
					</v-list-item>
				</div>
			</v-list>

			<v-list dense v-if="showCate" class="side-itms">
					<div>
						<v-list-item
						v-for="item in categories"
						:key="item.title">
							<v-list-item-content>
								<v-btn text><v-list-item-title>{{ item.title }}</v-list-item-title></v-btn>
							</v-list-item-content>
						</v-list-item>
					</div>
			</v-list>

			<div id="side-footer">
				<div v-if="this.$store.state.user.signedIn==true" class="side-footer-btns">
				  <div><setting-btn /><logout-btn @logout="isAuth = $event"/></div>
				</div>
				<div v-else class="side-footer-btns">
				  <div><signup-btn id="signup-btn" /><login-btn id="login-btn" @login="isAuth = $event" /></div>
				</div>
			</div>

		</v-navigation-drawer>

    </v-card>
</template>

<script lang="ts">
	import Vue from 'vue'
	import LoginBtn from '@/components/buttons/LoginBtn.vue'
	import LogoutBtn from '@/components/buttons/LogoutBtn.vue'
	import SignupBtn from '@/components/buttons/SignupBtn.vue'
	import SettingBtn from '@/components/buttons/SettingBtn.vue'
	// import LikeBtn from '@/components/buttons/LikeBtn.vue'
	// import BookmarkBtn from '@/components/buttons/BookmarkBtn.vue'
	// import PostDeleteBtn from '@/components/buttons/PostDeleteBtn.vue'
	// import PublishBtn from '@/components/buttons/PublishBtn.vue'
	// import Comment from '@/components/buttons/Comment.vue'
	import PostBtn from '@/components/buttons/write/PostBtn.vue'
	import DeleteBtn from '@/components/buttons/write/DeleteBtn.vue'
	import PreviewBtn from '@/components/buttons/write/PreviewBtn.vue'
	import SaveBtn from '@/components/buttons/write/SaveBtn.vue'
	import SearchModal from '@/views/SearchModal.vue'
  import http from "@/http/http-common";
	export default Vue.extend({
		data: () => ({
			drawer: false,
			group: null,
			isAuth: true,
			showCate:false,
			showMenu:false,
			showFooter: true,
			menusBL: [
				{ title: '홈', link: '/'},
				{ title: '최신글', link: '/article'},
			],
			menusAL: [
				{ title: '내 프로필', link: '/myprofile'},
				{ title: '내 원고', link: '/myonego'},
				{ title: '스크랩', link: '/scrap'},
			],
			categories:[
				{title:'일상'},
				{title:'시사·이슈'},
				{title:'IT 트렌드'},
				{title:'요리·레시피'},
				{title:'운동·건강'},
				{title:'사랑·이별'}
			],
			user:{
				name:'Mary Jane',
				nickname:'Mary',
				email:'mj123@gmail.com',
				intro:'Hello, I am an avid writer',
				pic:"https://randomuser.me/api/portraits/women/82.jpg"
			},
		}),
		name: "Header",
		components:{
			'login-btn':LoginBtn, 'logout-btn':LogoutBtn, 'signup-btn':SignupBtn, 'setting-btn':SettingBtn,
			'delete-btn':DeleteBtn, 'post-btn':PostBtn, 'preview-btn':PreviewBtn, 'save-btn':SaveBtn,
			'search-modal':SearchModal
		},
		methods:{
			openCategory(){
				// eslint-disable-next-line @typescript-eslint/no-non-null-assertion
				document.getElementById('menu-btn')!.style.color = "#9E9E9E";
				// eslint-disable-next-line @typescript-eslint/no-non-null-assertion
				document.getElementById('cate-btn')!.style.color = "#00d5aa";
				this.showMenu = false;
				this.showCate = true;
			},
			openMenu(){
				// eslint-disable-next-line @typescript-eslint/no-non-null-assertion
				document.getElementById('menu-btn')!.style.color = "#00d5aa";
				// eslint-disable-next-line @typescript-eslint/no-non-null-assertion
				document.getElementById('cate-btn')!.style.color = "#9E9E9E";
				this.showMenu = true;
				this.showCate = false;
			}
		}
	})
</script>

<style>
	.row {
		display: flex;
		flex-wrap: wrap;
		flex: 0 !important;
		margin: 0 !important;
	}
	.header-padding .v-toolbar__content{
		padding-right:5px;
	}
	.side-itms{
		text-align:center;
	}
	.v-btn::before{
		background-color: transparent !important;
	}
	.v-btn:hover{
		color: #00d5aa !important;
	}
	#write-btn:hover{
		color:#02bf99 !important;
	}
	#side-footer{
		width:250px;
	}
	.side-footer-btns{
		position: absolute !important;
		margin-left: auto;
		margin-right: auto;
		left: 0;
		right: 0;
		bottom: 3%;
		text-align: center;
	}
	#menu-btn{
		padding-left:30px !important;
		padding-right:30px !important;
	}
	#login-btn{
		padding-left:23px !important;
		padding-right:23px !important;
	}
	.v-navigation-drawer {
		z-index: 999999 !important;
	}
</style>