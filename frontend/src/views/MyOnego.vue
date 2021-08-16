/* eslint-disable @typescript-eslint/no-non-null-assertion */
<template>
  <div>
    <v-flex id="myonego-margin" class="mx-auto">
	  <div align="center" class="spacing">
		<a href="#saved" id="saved-btn" @click="showSaved"><label id="saved-label">저장글</label></a>&nbsp;&nbsp;&nbsp;
		<a href="#posted" id="posted-btn" @click="showPosted"><label id="posted-label">발행글</label></a>
	  </div>
	  <br>
	  <div v-if="isSaved">
		<div v-if="savedList.length === 0">
			<div align="center" style="font-size:1rem;opacity:60%;margin-top:90px;">저장된 글이 없습니다.</div>
		</div>
		<div v-else>
	      <div v-for="(article,i) in savedList" :key="i">
			<a :href="article.link" class="edit-article">
			  <div>
		      <div class="article-title" style="font-size:1.35rem;">{{article.title}}</div>
		      <div class="article-text">{{article.text}}</div><br>
			  <div style="font-size:0.8rem;opacity:50%">{{article.date}}</div>
			  </div>
			</a>
			<v-divider class="divider-myonego"/>
	      </div>
		</div>
	  </div>
	  <div v-if="isPosted">
		<div v-if="postedList.length === 0">
			<div align="center" style="font-size:1rem;opacity:60%;margin-top:90px;">발행된 글이 없습니다.</div>
		</div>
		<div v-else>
	      <div v-for="(article,i) in postedList" :key="i">
			<a href="#" class="edit-article">
			  <div>
		      <div class="article-title" style="font-size:1.35rem;">{{article.title}}</div>
		      <div class="article-text">{{article.text}}</div><br>
			  <div style="font-size:0.8rem;opacity:50%">{{article.date}}</div>
			  </div>
			</a>
			<v-divider class="divider-myonego"/>
	      </div>
		</div>
	  </div>
	</v-flex>
  </div>
</template>

<script lang="ts">
	import Vue from 'vue'

	export default Vue.extend({
		data: () => ({
			savedList:[
				{title:'콘텐츠 유저',text:'테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트테스트',
				date:'7.27.2021', link:'content1/'},
				{title:'콘텐츠 작성자',text:'테스트테스트테스트테스트테스트테스트테스트테스트',date:'7.27.2021', link:'content2/'},
				{title:'테스트 입니다.',text:'테스트테스트테스트테스트테스트테스트테스트테스트',date:'7.27.2021'}
			],
			postedList:[],
			isSaved: true,
			isPosted: false
		}),
		methods:{
			showSaved(){
				this.isSaved = true;
				this.isPosted = false;
				// eslint-disable-next-line @typescript-eslint/no-non-null-assertion
				document.getElementById('posted-label')!.style.color = "#9E9E9E";
				// eslint-disable-next-line @typescript-eslint/no-non-null-assertion
				document.getElementById('posted-label')!.style.textDecoration = "none";
				// eslint-disable-next-line @typescript-eslint/no-non-null-assertion
				document.getElementById('saved-label')!.style.color = "#00d5aa";
				// eslint-disable-next-line @typescript-eslint/no-non-null-assertion
				document.getElementById('saved-label')!.style.textDecoration = "underline";
			},
			showPosted(){
				this.isSaved = false;
				this.isPosted = true;
				// eslint-disable-next-line @typescript-eslint/no-non-null-assertion
				document.getElementById('saved-label')!.style.color = "#9E9E9E";
				// eslint-disable-next-line @typescript-eslint/no-non-null-assertion
				document.getElementById('saved-label')!.style.textDecoration = "none";
				// eslint-disable-next-line @typescript-eslint/no-non-null-assertion
				document.getElementById('posted-label')!.style.color = "#00d5aa";
				// eslint-disable-next-line @typescript-eslint/no-non-null-assertion
				document.getElementById('posted-label')!.style.textDecoration = "underline";
			}
		}
	})
</script>

<style>
#myonego-margin{
	max-width:55%;
	margin-top: -25px;
}
#saved-label{
	color:#00d5aa;
	text-decoration: underline;
}
#posted-label{
	color:#9E9E9E;
}
#saved-btn:hover > #saved-label,
#posted-btn:hover > #posted-label{
	cursor:pointer !important;
	text-decoration:underline !important;
	color: #00d5aa !important;
}
.divider-myonego{
	margin-top:30px;
	margin-bottom: 30px;
}
.edit-article,#saved-btn,#posted-btn{
	text-decoration: none !important;
	color: black !important;
}
.edit-article:hover .article-title{
	text-decoration:underline !important;
	color: #00d5aa !important;
}
.article-text{
	font-size:1rem;
	opacity:60%;
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 1;
    /* number of lines to show */
    -webkit-box-orient: vertical;
}
</style>