<template>
  <div id="item">
    
      
        <v-flex v-for="article in articles" v-bind:key="article.title">
          <v-card
            class="mx-auto"
            max-width="55%"
            max-height="600"
            tile
          >
            <div class="card">
              <v-card-text class="text newest-article">
                <h2 v-html="article.title"></h2><br>
                <!-- <h3 style="font-weight: normal" v-html="article.subtitle"></h3> -->
                <p v-html="article.contents[0].content"></p>
              </v-card-text>

              <v-card-actions class="avatar-box">
                  <v-list-item-avatar class="page-avatar" color="grey darken-3" style="width: 15px; height: 15px;">
                    <v-img
                      class="avatar"
                      alt="avatar"
                      src="https://avataaars.io/?avatarStyle=Transparent&topType=ShortHairShortCurly&accessoriesType=Prescription02&hairColor=Black&facialHairType=Blank&clotheType=Hoodie&clotheColor=White&eyeType=Default&eyebrowType=DefaultNatural&mouthType=Default&skinColor=Light"
                    ></v-img>
                  </v-list-item-avatar>
                  <v-list-item-content class="author-date">
                    <span class="nickname" v-html="article.nickName"></span>
                    <span class="modDatetime" v-html="article.modDatetime"></span>
                    <!-- <span >{{currentDate(article.modDatetime)}}</span> -->
                  </v-list-item-content>
              </v-card-actions>
              <br/>
            </div>
          </v-card>
          <br>
        </v-flex>
        
        <div class="text-center">
          <v-pagination v-model="page" :length="totalPageNum" prev-icon="mdi-menu-left" next-icon="mdi-menu-right" @input="changePage">
          </v-pagination>
        </div>
    
  </div>
</template>

<script lang="ts">
import Vue from 'vue'
import http from '../../http/http-common'
import moment from 'moment'

export default Vue.extend({
      data: () => ({
        articles: {},
        errored: false,
        loading: true,
        content: '',
        page: 1,
        totalPageNum: 1
		  }),
      methods: {
        async getArticles(pageNum: number){
          await http
              .get('/board', {
                params: { 'pageNumber': pageNum }})
              .then(response => {
                  response.data.forEach((d: any) => {
                    if(d.contents[0].content.length > 150){
                      d.contents[0].content = d.contents[0].content.substr(0,148)+"..."
                    }
                  })
                  this.articles = response.data;
              })
              .catch(() => {
                this.errored = true
              })
              .finally(() => this.loading = false)
              
            },
          // currentDate(abc: string|number|Date) {
          //   const date1 =  new Date(abc)
          //   const test = date1.getMonth()
          //   const current = new Date();
          //   // const difference = moment(current).diff(moment(date1))
          //   // console.log("difference: "+moment(difference))
          //   const difference = current.getTime() - date1.getTime()
          //   return current
          // },
          changePage(value: number){
            this.page = value
            console.log("changePage click")
            console.log("page: "+this.page)
            this.getArticles(this.page)
          },
          async boardCount(){
            await http
                .get('/board/count')
                .then(response => {
                        this.totalPageNum = Math.floor(response.data / 5) + 1
                      }
                    )
                .catch(() => { this.errored = true })
                .finally(() => this.loading = false)                
          }
              
      },
      async created(){
        console.log("mounted")
        this.getArticles(1)
        this.boardCount()
      }
    })

</script>

<style>
  #item {
		font-family: "Noto Sans KR", sans-serif !important;
	}
  .card{
    margin: 0px 15px 0px 15px;
  }
  .text{
    padding-top: 30px;
    padding-bottom: 5px;
  }
  .text-post{
    font-size : 1.1rem;
    line-height: 1.7em;
    font-weight: 300;
    letter-spacing: 0;
    /* 글자수 제한*/
    overflow: hidden;
    text-overflow: ellipsis;
    display: -webkit-box;
    -webkit-line-clamp: 3;
    -webkit-box-orient: vertical;
    word-wrap:break-word;
    height: 5.1em; /*height는 1.7em * 3줄 = 5.1em */
  }
  .avatar-box{
    padding-left: 17px;
  }
  .newest-article{
    padding-bottom: 0px !important;
  }
  .page-avatar{
    margin-top: 5px;
    margin-bottom: 0px;
    height: 50px !important;
    min-width: 50px !important;
    width: 50px !important;
  }
</style>