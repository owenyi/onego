<template>
  <div>
	<v-flex id="write-margin">
	  <div style="width:55%">
	  <editor-content :editor="editor"/>
	  </div>
	</v-flex>
  </div>
</template>

<script src="https://cdn.jsdelivr.net/npm/tiptap-vuetify"></script>
<script lang="ts">
	import Vue from 'vue'
	import Component from 'vue-class-component'
	import { Editor, EditorContent } from '@tiptap/vue-2'
	import StarterKit from '@tiptap/starter-kit'

	@Component({
		components:{
			EditorContent
		}
	})
	export default class Write extends Vue {
		private editor: any = null

		mounted(){
			this.editor = new Editor({
				content: '<p>tiptap</p>',
				extensions: [
					StarterKit
				]
			})
		}

		beforeDestroy(){
			this.editor.destroy()
		}
	}
</script>

<style>
#write-margin{
	margin-top:-36px !important;
}
</style>