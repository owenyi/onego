<template>
<div id="item">
  <v-container>
    <v-card 
      class="mx-auto"  
      max-width="65%"
      min-height="1300px"
      flat
    >
      <v-toolbar
        prominent
        flat
      >
        <v-spacer></v-spacer>
        <v-toolbar-title style="font-weight: 700; font-size : 1.7rem; padding-left: 7.1em;">
          진짜 내 사람 구별하는 방법
        </v-toolbar-title>
        <v-spacer></v-spacer>
        
        <div class="icon">
          <v-btn icon  @click="write">
            <write-btn/>
          </v-btn>

          <v-btn icon  @click="bookmark">
            <delete-btn/>
          </v-btn>

          <v-btn icon class="icon-publish" @click="comment">
            <publish-btn/>
          </v-btn>
        </div>
        
      </v-toolbar>


      <br/><hr/><br/><br/>
      <div id="text">
        
        얕은 만남이 판치는 시대에서 '내 사람'을 구별하는 방법  |  우리 아빠에겐 친구가 몇 명없다. 나는 아빠의 친구분들과는 별로 안면이 없지만 유일하게 기억에 남는 딱 한분이 계신다. 그분을 처음 본건 친 할머니의 장례식장에서 였다. 서울에서 4시간가량 떨어진 먼 장례식장에도 제일 먼저 달려와 준 그 사람. 장례식장에서 계속 울고불고 하던 아빠를 잠깐이라도 웃음짓게 만들어준 그 사람을 보며 '진짜 내 사람'의 정석을 배웠다. '진짜 내 사람'은 아무 말을 하지 않아도 표정으로 마음을 읽는다. 장례식장에서 점점 언성이 높아지고, 헛소리를 하는 아빠를 보며 우리 가족들은 '너무 취했다.'라고 말했지만 유일하게 그 분은 '지금은 진짜 취한 게 아니에요.'라고 말하며 아빠에 대해 정확하게 파악하고 있었다.
        
        [앵커]
        4인 홑벌이 가구 기준으로 지난달 건강보험료를 30만 8,300원을 낸 사람까지 1인당 25만 원의 국민지원금이 지급 됩니다.

        지역가입자는 34만 2천 원이 기준선 입니다.

        정부가 확정한 국민지원금 지급 기준을 오인석 기자가 전해 드립니다.

        [기자]
        6월 기준으로 건강보험료 합산액이 하위 80% 이하가 돼야 국민지원금이 지급 됩니다.

        홀벌이 4인 가구 직장가입자는 지난달 건보료 본인부담금이 30만 8,300원 이하일 경우, 가구 1인당 25만 원의 지원금을 받을 수 있습니다.

        2인은 19만 1,100원, 3인 24만 7천 원, 6인은 41만 4,300원이 지급 기준선 입니다.

        홑벌이 4인 가구 지역가입자는 34만 2천 원까지 지원금을 받습니다.

        2인은 20만 1,000원, 3인 27만 1,400원, 5인은 42만 300원까지 지급 됩니다.

        1인 가구와 맞벌이 가구는 특례가 적용 됩니다.

        1인은 직장은 14만 3,900원, 지역은 13만 6,300원의 건보료까지 지원금이 지급 됩니다.

        직장가입자 2인 맞벌이 가구는 24만 7천 원, 지역가입자 2인 맞벌이는 27만 1,400원이 지급 기준선 입니다.

        맞벌이에는 부모와 자녀, 성인 자녀가 같이 소득 활동을 하는 경우도 포함 됩니다.

        [안도걸 / 기획재정부 1차관 : 1인 가구의 경우에는 노인, 비경활인구가 많은 특성을 감안해서 연소득 5,000만 원 이하분에게 지급하게 되겠습니다. 그리고 맞벌이 가구의 경우에는 가구원 수를 1명 더 추가된 소득 기준을 적용하게 되겠습니다.]

        6월 건보료는 이미 고지됐고, 건강보험 홈페이지에서 확인할 수 있습니다.

        건보료 기준에 부합하더라도 지난해 재산세 과세표준 합계액이 9억 원을 넘거나, 금융소득 합계액이 2천만 원을 초과하면 지급 대상자에서 배제 됩니다.

        시가로 20∼22억 원의 집을 소유하거나, 연 1.5% 수익을 내는 금융상품에 13억 원을 예치한 자산가가 해당 됩니다.

        정부는 가구 소득 하위 80% 이하와 1인 가구, 맞벌이 가구의 특례적용을 감안 할 때 2천 34만 가구가 국민지원금을 지급 받을 것으로 추정했습니다.
      
      </div> <br/><br/><br/>
      
      <!-- 태그 -->
      <v-row justify="space-around">
        <v-col
          cols="12"
        >
          <v-chip-group
            mandatory
            active-class="primary--text"
          >
            <v-chip
              v-for="tag in tags"
              :key="tag"
              active-class="tag"
              color="#00d5aa"
              outlined
              link
            >
              {{ tag }}
            </v-chip>
            <v-spacer></v-spacer>
              <v-v-btn-toggle
                color="#00d5aa"
                text-color="#ffffff"
                v-model="toggle_comment"
                @click="comment"
              >
                <v-btn
                color="#00d5aa"
                outlined
                >
                  댓글
                </v-btn>
              </v-v-btn-toggle>
          </v-chip-group>
        </v-col>
      </v-row>
      <br/><br/>

      <!-- 댓글 layout component로 만들기,,,-->
      <span style="font-size : 1.2rem; font-weight: 400; padding-left: 1rem;">댓글</span>
      <v-banner>
      </v-banner>
      <comment />
      <br/>
      <!-- 프로필 -->
      <profile />
   </v-card>
  </v-container>
</div>
</template>


<script lang="ts">
import Vue from 'vue'
import Vuetify from 'vuetify/lib'
import writeBtn from '@/components/buttons/writeBtn.vue'
import PostDeleteBtn from '@/components/buttons/PostDeleteBtn.vue'
import PublishBtn from '@/components/buttons/PublishBtn.vue'
import Profile from '@/components/layout/Profile.vue'
import Comment from '@/components/layout/Comment.vue'
//import TagsBtn from '@/components/buttons/TagsBtn.vue'

Vue.use(Vuetify)

export default Vue.extend({
    name: 'Mypost',
    data: () => ({
        return: {
          title: "진짜 내 사람 구별하는 방법",
          text: "얕은 만남이 판치는 시대에서 '내 사람'을 구별하는 방법  |  우리 아빠에겐 친구가 몇 명없다. 나는 아빠의 친구분들과는 별로 안면이 없지만 유일하게 기억에 남는 딱 한분이 계신다. 그분을 처음 본건 친 할머니의 장례식장에서 였다. 서울에서 4시간가량 떨어진 먼 장례식장에도 제일 먼저 달려와 준 그 사람. 장례식장에서 계속 울고불고 하던 아빠를 잠깐이라도 웃음짓게 만들어준 그 사람을 보며 '진짜 내 사람'의 정석을 배웠다. '진짜 내 사람'은 아무 말을 하지 않아도 표정으로 마음을 읽는다. 장례식장에서 점점 언성이 높아지고, 헛소리를 하는 아빠를 보며 우리 가족들은 '너무 취했다.'라고 말했지만 유일하게 그 분은 '지금은 진짜 취한 게 아니에요.'라고 말하며 아빠에 대해 정확하게 파악하고 있었다.",
        },
        tags: [
        '일상',
        '시사',
        '이슈',
        'IT 트렌드',
        '요리',
        '운동',
        '사랑',
        '이별'
      ],
    }),
		components:{
      'write-btn':writeBtn,
      'delete-btn':PostDeleteBtn,
      'publish-btn':PublishBtn,
      'profile':Profile,
      'comment':Comment
      //'tag-btn':TagsBtn
		},
    methods: {
    
  }
})


</script>


<style >
	#item {
			font-family: "Noto Sans KR", sans-serif !important;
	}
  .icon{
    padding-top: 1em;
    padding-right: 1.5em;

  }
  .icon-publish{
    padding-left: 2em;
    height: 1.8em;
  }
  #text{
    font-size : 1.1rem; 
    line-height: 2.3em; 
    font-weight: 300; 
    letter-spacing: 0;
  }
</style>