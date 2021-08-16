<template>
<v-row class="mx-auto justify-center">
  <v-container>
    <v-card
      min-height="1300px"
      flat 
      class="post"
      >
        <!-- Title Image -->
        <v-card tile class="mb-12" id="image-card">
            <v-img height="50vh" :src="article.titleImage">
              <v-flex id="title-preview-margin" class="mx-auto">
                <template v-if="$store.state.user.userInfo.email === article.userId">
                  <span class="postbtn"><v-btn icon color="#00d5aa"><i class="fas fa-trash-alt" style="font-size: 1.8em;" @click="postdelete(userEmail)"></i></v-btn></span>
                  <span class="postbtn"><v-btn rounded outlined color="#00d5aa" @click="postput(boardId,article.title,article.subtitle,article.titleImage,article.contents,article.tags)">수정</v-btn></span>
                </template>
                <template v-else>
                  <span class="postbtn"><BookmarkBtn/></span> 
                  <span class="postbtn"><v-btn icon color="#00d5aa"><i class="fa fa-heart" style="font-size: 1.8em;" @click="getlike(userEmail)"></i></v-btn></span>
                </template>
              <span><h1 style="font-size:40px; padding-left:1px;">{{ article.title }}</h1></span>
              
              <div style="opacity:80%;"><h3>{{ article.subtitle }}</h3></div>
              <div style="opacity:60%;margin-top:30px;">
                <span><h5> {{ article.nickName }}· {{ article.modDatetime }} </h5></span>
              </div>
              </v-flex>
            </v-img>
        </v-card>
        
      <!-- Content -->
      <v-flex id="content-preview-margin" class="mx-auto">
          <div v-for="(content,i) in article.contents" :key="i">
            <h3 v-html="content.subtitle"></h3>
            <p style="padding-top: 10px; padding-bottom: 30px; line-height:30px;" v-html="content.content"></p>
          </div>
      </v-flex>

      <!-- Tags -->
      <v-flex id="tag-preview-margin" class="mx-auto mt-8 pb-8">
        <span  v-for="tag in article.tags" :key="tag">
          <v-chip outlined small color="#00d5aa" class="mr-2">{{ tag }}</v-chip>
        </span>
      </v-flex>

      <!-- <div style="height: 60px;"></div> -->
      <Comment :boardId = "article.id"/>   
      
      <!-- <div style="height: 60px;"></div> -->
      <Profile :id = "article.userEmail"/>
   </v-card>
  </v-container>
 </v-row>
</template>


<script lang="ts">
import Vue from 'vue';
import Profile from '@/components/layout/Profile.vue'
import Comment from '@/components/layout/Comment.vue'
import http from '../../http/http-common'
// import LikeBtn from '@/components/buttons/LikeBtn.vue'
import BookmarkBtn from '@/components/buttons/BookmarkBtn.vue'
//import PostDeleteBtn from '@/components/buttons/PostDeleteBtn.vue'
//import PublishBtn from '@/components/buttons/PublishBtn.vue'


export default Vue.extend({

    data: () => ({
          errored: false,
          loading: true,
          content: '',
          page: 1,
          article : {},
          user: {},
          id : '',
          userId : ''
    }),
    components:{
      Profile, Comment, 'BookmarkBtn':BookmarkBtn,  
      // 'LikeBtn':LikeBtn, 'PostDeleteBtn':PostDeleteBtn,'PublishBtn':PublishBtn    
    },
    methods: {
        async getArticle(boardId: string){
          await http
              .get('/board', {
                params: { 'boardId': boardId }})
              .then(response => {
                  this.article = response.data[0];
              })
        },
        getlike(userEmail:string){
          console.log("like")
          http
          .post('/boardId', {'userEmail': userEmail})
          .then(response => {
              console.log(response.data)
              this.getlike(this.$route.params.boardId)
          })
        },

        postdelete(boardId:string){
          console.log("postdelete")

          var con_test = confirm("글을 삭제하시겠습니까?");
          if(con_test == true){
              http
                .delete('/boardId', {
                data: { 'boardId': boardId}})
                .then(response => {
                    this.article = response.data[0];
                })
          }
          else if(con_test == false){
              http
                .get('/board', {
                  params: { 'boardId': boardId }})
                .then(response => {
                    this.article = response.data[0];
                })
          }
          
        },
         postput( boardId:string,title:string,subtitle:string,titleImage:string,contents:string,tags:string){
              http
              .put('/comment', { 
                data:{'boardId': boardId, 'title': title, 'subtitle': subtitle, 'titleImage': titleImage, 'contents': contents, 'tags':tags}})
              .then(response => {
                  console.log(response.data)
                  this.article = response.data[0];
              })
          },
    },
    created(){  
      this.getArticle(this.$route.params.boardId)
    }
})
</script>


<style >
.mx-auto justify-center{
  margin: 0 auto;
}
	.post {
		font-family: "Noto Sans KR", sans-serif !important;
	}
  .postbtn{
    vertical-align: middle;
    float: right;
    padding-left: 10px;
  }
  #preview-btn:hover{
	color:#757575 !important;
  }

  #image-card{
    position:relative !important;
  }

  #title-preview-margin{
    max-width:50% !important;
    position:absolute !important;
    bottom:10% !important;
    left:25% !important;
  }

  #image-card .v-image__image{
    opacity:80% !important;
  }

  #title-content-preview-margin,
  #content-preview-margin,
  #tag-preview-margin{
    max-width:50% !important;
  }
</style>