<template>
  <div id="item">
	<v-card elevation="0">
	  <v-flex id="search-bar-container" class="mx-auto justify-center">
		<!-- search bar -->
		<v-text-field id="text-field" class="text_field"
			:value="search"
			color="#00d5aa"
			height="30px"
			single-line
			flat solo
			autofocus
			@keyup.enter="doSearch"
			>
		</v-text-field>
	  </v-flex>
	<!-- search result -->
	</v-card>
	<v-divider style="margin-bottom:50px;"></v-divider>
	<div v-if="filteredSearch.length===0">
	<v-flex>
		<v-card
			class="mx-auto"
			max-width="55%"
			max-height="600"
		>
			<v-card-text>검색 결과가 없습니다.</v-card-text>
		</v-card>
	</v-flex>
	</div>
	<div v-else>
	<v-flex>

      <v-card
        class="mx-auto"
        max-width="55%"
        max-height="600"
		tile
		elevation="3"
		:key="i" v-for="(article, i) in filteredSearch"
      >
	  	<a href='#' id="list-item" >
        <div class="card">
          <v-card-text class="text" >
            <div id="article-title" v-html="highlightTitle(article.title)">{{article.title}}</div>
            <br/>
            <div class="text-post" v-html="highlightTitle(article.text)">{{article.text}}</div>
          </v-card-text>

          <v-card-actions class="avatar-box">
              <v-list-item-avatar color="grey darken-3">
                <v-img
                  class="avatar"
                  alt="avatar"
                  src="https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairShortCurly&accessoriesType=Prescription02&hairColor=Black&facialHairType=Blank&clotheType=Hoodie&clotheColor=White&eyeType=Default&eyebrowType=DefaultNatural&mouthType=Default&skinColor=Light"
                ></v-img>
              </v-list-item-avatar>
              <v-list-item-content class="author-date">
                <span class="author">{{article.author}}</span>
                <span class="date">{{article.date}}</span>
              </v-list-item-content>
          </v-card-actions>
          <br/>
        </div>
		</a>
      </v-card>

    </v-flex>
	</div>
  </div>
</template>

<script lang="ts">
	import Vue from 'vue'
	import '@/assets/css/SearchResult.css'

	export default Vue.extend({
		name:"SearchResult",
		props:{
			search : {
				type:String, required:true
			}
		},
		data: ()=>({
			blogList:[
				{title:'고구마는 맛있다', text:'안녕 하세요 저는 고구마 입니다. 안녕 하세요 저는 고구마 입니다. 안녕 하세요 저는 고구마 입니다.',author:'user1',date:'2.15.2020'},
				{title:'호박 고구마 곡', text:'안녕 하세요 저는 고구마 입니다. 안녕 하세요 저는 고구마 입니다. 안녕 하세요 저는 고구마 입니다.',author:'user2',date:'2.15.2020'},
				{title:'겨울엔 군고구마 군', text:'안녕 하세요 저는 고구마 입니다. 안녕 하세요 저는 고구마 입니다. 안녕 하세요 저는 고구마 입니다.',author:'user3',date:'2.15.2020'},
				{title:'고구마 감자 군 고구마!', text:'안녕 하세요 저는 고구마 입니다. 안녕 하세요 저는 고구마 입니다. 안녕 하세요 저는 고구마 입니다.',author:'user1',date:'2.15.2020'},
				{title:'감자 입니다!', text:'안녕 하세요 저는 고구마 감자 입니다. 안녕 하세요 저는 고구마 감자 입니다. 안녕 하세요 저는 고구마 감자 입니다.',author:'user4',date:'2.15.2020'},
				{title:'감자엔 소금', text:'안녕 하세요 저는 감자 입니다. 안녕 하세요 저는 감자 입니다. 안녕 하세요 저는 감자 입니다.',author:'user1',date:'2.15.2020'},
				{title:'통감자 맛있나 ㄱ', text:'고구마 안녕 하세요 저는 감자 입니다. 고구마 안녕 하세요 저는 감자 입니다. 고구마 안녕 하세요 저는 감자 입니다.',author:'user1',date:'2.15.2020'},
				{title:'호박의 효능', text:'호박은 좀 별로..밤..감자는 퍽퍽 호박은 좀 별로..밤..감자는 퍽퍽 호박은 좀 별로..밤..감자는 퍽퍽',author:'user2',date:'2.15.2020'}
			],
			newSearch:''
		}),
		components:{
		},
		methods:{
			doSearch(){
				this.newSearch = (document.getElementById('text-field') as HTMLTextAreaElement).value;
				if(this.newSearch == ""){
					alert("검색어를 입력해주세요.");
					return;
				}
				location.href = "/search/"+this.newSearch;
			},
			highlightTitle(title:string){
				const searchWord1 = this.search.replace(/\s/g, "").split("").join('|');
				const searchWord2 = this.search.split(" ").join('|');
				return title.replace(new RegExp(searchWord1, "gi"), match => {
					return '<span class="highlight">' + match + '</span>';
				}) && title.replace(new RegExp(searchWord2, "gi"), match => {
					return '<span class="highlight">' + match + '</span>';
				});
			},
			hightlightText(text:string){
				const searchWord1 = this.search.replace(/\s/g, "").split("").join('|');
				const searchWord2 = this.search.split(" ").join('|');
				return text.replace(new RegExp(searchWord1, "gi"), match => {
					return '<span class="highlight">' + match + '</span>';
				}) && text.replace(new RegExp(searchWord2, "gi"), match => {
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
				return this.blogList.filter((article)=>{
					if(this.search != ""){
						const titleContent = article.title + article.text;
						for(var i=0; i<searchArray.length; i++){
							searchStr += str1 + searchArray[i] + str2;
						}
						matches = titleContent.match('^.*'+searchStr+'.*$')
									&& this.search.match(/[ㄱ-ㅎㅏ-ㅣ가-힣a-zA-Z0-9]/gi);
						return matches !== null;
					}
				});
			}
		}
	})
</script>

<style>

</style>