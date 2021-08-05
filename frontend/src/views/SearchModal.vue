<template>
  <v-row id="modal-margin" class="mx-auto justify-center">
	<!-- modal -->
    <v-dialog
      v-model="dialog"
      fullscreen
      hide-overlay
	  persistent
      transition="dialog-bottom-transition"
    >
	  <!-- magnify button click shows modal -->
	  <template v-slot:activator="{ on, attrs }">
		<v-btn icon
			v-bind="attrs"
          	v-on="on">
			<v-icon>mdi-magnify</v-icon>
		</v-btn>
	  </template>

      <v-card>
		<!-- header -->
        <v-toolbar
          color="white" fixed elevation="0"
        >
          <v-app-bar-nav-icon @click="openDrawer"></v-app-bar-nav-icon>

		  <a href="/"><img src="@/assets/logo/onego_logo.jpeg"
		  	width="120px" height="43.2px"
			style="vertical-align:middle;"></a>
          <v-spacer></v-spacer>
          <v-btn
            icon
            @click="dialog = false"
          >
            <v-icon>mdi-close</v-icon>
          </v-btn>
        </v-toolbar>
		<v-flex id="text-field-container" class="mx-auto justify-center">
			<!-- search bar -->
			<v-text-field id="text-field" class="text_field"
				v-model="search"
				color="#00d5aa"
				height="50px"
				placeholder="검색어를 입력하세요"
				single-line
				autofocus
				:append-icon="'mdi-magnify'"
				@click:append="doSearch"
				@keyup.enter="doSearch"
				@keyup="hideTable">
			</v-text-field>
			<!-- search list -->
			<div id="search-list" v-if="isSearch">
				<v-card class="mx-auto" tile>
					<v-list dense>
						<div v-if="filteredSearch.length===0 && search!==''">
							<v-card-text>검색 결과가 없습니다.</v-card-text>
						</div>
						<v-list-item
							:key="i" v-for="(blog, i) in filteredSearch.slice(0,8)"><!-- shows only 8 search results from filteredSearch -->
							<a href="#" id="blog-item" class='a-tag' v-html="highlight(blog.title)"><!-- v-html="highlight(blog.title)" -->
								<v-list-item-content>{{blog.content}}</v-list-item-content></a>
						</v-list-item>
					</v-list>
				</v-card>
			</div>
		</v-flex>
		<!-- user list -->
		<div align="center" id="table-container" v-if="isTable">
			<v-simple-table style="text-align:center;">
				<tr>
					<td v-for="user in userList" :key="user.name">
						<a href="#" id="user-item" class='a-tag'>
							<v-list-item-avatar size=100 class="mx-auto" id="user-img-div">
								<img :src="user.img" id="user-img">
							</v-list-item-avatar>
							<v-list-item-content>
								<v-list-item-title id="user-name">{{user.name}}</v-list-item-title>
							</v-list-item-content>
							<div><div id="user-intro">{{user.intro}}</div></div>
						</a>
					</td>
				</tr>
			</v-simple-table>
		</div>

      </v-card>

    </v-dialog>
  </v-row>
</template>

<script lang="ts">
	import Vue from 'vue'
	import '@/assets/css/SearchModal.css'

	export default Vue.extend({

		data: ()=>({
			search: '',
			isSearch: false,
			isTable: true,
			dialog: false,
			notifications: false,
			sound: true,
			widgets: false,
			userList:[
				{name: 'User1', img: 'https://randomuser.me/api/portraits/women/82.jpg', intro: 'Hello everyone Hello everyone Hello everyone'},
				{name: 'User2', img: 'https://randomuser.me/api/portraits/men/82.jpg', intro: 'Hello everyone'},
				{name: 'User3', img: 'https://randomuser.me/api/portraits/women/83.jpg', intro: 'Hello everyone'},
				{name: 'User4', img: 'https://randomuser.me/api/portraits/men/83.jpg', intro: 'Hello everyone'},
				{name: 'User5', img: 'https://randomuser.me/api/portraits/women/84.jpg', intro: 'Hello everyone'},
			],
			blogList:[
				{title:'고구마는 맛있다', text:'안녕 하세요 저는 고구마 입니다. 안녕 하세요 저는 고구마 입니다. 안녕 하세요 저는 고구마 입니다.',author:'user1',date:'2.15.2020'},
				{title:'호박 고구마 곡', text:'안녕 하세요 저는 고구마 입니다. 안녕 하세요 저는 고구마 입니다. 안녕 하세요 저는 고구마 입니다.',author:'user2',date:'2.15.2020'},
				{title:'겨울엔 군고구마 군', text:'안녕 하세요 저는 고구마 입니다. 안녕 하세요 저는 고구마 입니다. 안녕 하세요 저는 고구마 입니다.',author:'user3',date:'2.15.2020'},
				{title:'고구마 감자 군 고구마!', text:'안녕 하세요 저는 고구마 입니다. 안녕 하세요 저는 고구마 입니다. 안녕 하세요 저는 고구마 입니다.',author:'user1',date:'2.15.2020'},
				{title:'감자 입니다!', text:'안녕 하세요 저는 고구마 감자 입니다. 안녕 하세요 저는 고구마 감자 입니다. 안녕 하세요 저는 고구마 감자 입니다.',author:'user4',date:'2.15.2020'},
				{title:'감자엔 소금', text:'안녕 하세요 저는 감자 입니다. 안녕 하세요 저는 감자 입니다. 안녕 하세요 저는 감자 입니다.',author:'user1',date:'2.15.2020'},
				{title:'통감자 맛있나 ㄱ', text:'고구마 안녕 하세요 저는 감자 입니다. 고구마 안녕 하세요 저는 감자 입니다. 고구마 안녕 하세요 저는 감자 입니다.',author:'user1',date:'2.15.2020'},
				{title:'호박의 효능', text:'호박은 좀 별로..밤..감자는 퍽퍽 호박은 좀 별로..밤..감자는 퍽퍽 호박은 좀 별로..밤..감자는 퍽퍽',author:'user2',date:'2.15.2020'}
			]
		}),
		components: {
		},
		methods: {
			openDrawer(){
				this.$emit('openDrawer', true);
			},
			doSearch(){
				if(this.search == ""){
					alert("검색어를 입력해주세요.");
					return;
				}
				location.href = "/search/"+this.search;
			},
			hideTable(){
				if(this.search != ""){
					this.isTable = false;
					this.isSearch = true;
				}else if(this.search == ""){
					this.isTable = true;
					this.isSearch = false;
				}
			},
			highlight(title:string){
				const searchWord1 = this.search.replace(/\s/g, "").split("").join('|'); //
				const searchWord2 = this.search.split(" ").join('|');
				return title.replace(new RegExp(searchWord1, "gi"), match => {
					return '<span class="highlight">' + match + '</span>'; //replaces this.search with highlighted this.search
				}) && title.replace(new RegExp(searchWord2, "gi"), match => {
					return '<span class="highlight">' + match + '</span>';
				});
			}
		},
		computed:{
			filteredSearch(){
				let matches : RegExpMatchArray | null;
				const searchArray : string[] = this.search.split(" ");
				var str1 = "(?=.*";
				var str2 = ")";
				var searchStr = '';
				//console.log(searchArray);
				//console.log(searchWord1);
				return this.blogList.filter((blog)=>{
					if(this.search != ""){
						//console.log(this.search);
						const titleContent = blog.title + blog.text;
						for(var i=0; i<searchArray.length; i++){
							searchStr += str1 + searchArray[i] + str2;
						}
						//console.log(searchStr);
						matches = titleContent.match('^.*'+searchStr+'.*$')
									&& this.search.match(/[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]/gi);
						return matches !== null; //if input search matches blog title
					}
				});
			}
		},
		filters:{

		}
	})
</script>

<style>

</style>