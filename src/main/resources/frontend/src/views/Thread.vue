<template>
  <div class="container mb-5 " v-if="thread">
    <div class="col text-center justify-content-center mt-5">
      <h1>{{ thread.title }}</h1>
      <div v-if="thread.locked === true" class="col mt-5">
        <svg
          xmlns="http://www.w3.org/2000/svg"
          width="40"
          height="40"
          fill="currentColor"
          class="bi bi-lock"
          viewBox="0 0 16 16"
        >
          <path
            d="M8 1a2 2 0 0 1 2 2v4H6V3a2 2 0 0 1 2-2zm3 6V3a3 3 0 0 0-6 0v4a2 2 0 0 0-2 2v5a2 2 0 0 0 2 2h6a2 2 0 0 0 2-2V9a2 2 0 0 0-2-2zM5 8h6a1 1 0 0 1 1 1v5a1 1 0 0 1-1 1H5a1 1 0 0 1-1-1V9a1 1 0 0 1 1-1z"
          />
        </svg>
        <div class="text-danger">Tråden är låst</div>
      </div>
    </div>

    <form
      v-if="isAdmin || isUserAndThreadIsOpen"
      @submit.prevent="createMessage"
      class="mb-4"
    >
      <div class="form-group">
        <label for="message">Meddelande</label>
        <input
          type="text"
          class="form-control"
          id="message"
          required
          v-model="message.messageContent"
        />
      </div>
      <div v-if="user? user.roles.includes('ADMIN'): false" class="form-check mb-3">
        <input type="checkbox" class="form-check-input" id="warningPost" v-model="message.warningPost" />
        <label class="form-check-label" for="warningPost">Varningsinlägg</label>
      </div>
      <button type="submit" class="btn btn-dark">
        Skicka meddelandet
      </button>
    </form>

    <Message
      v-for="message in thread.messages"
      :key="message.message_id"
      :message="message"
      class="border-bottom border-dark"
    />
  </div>
</template>
<script>
import Message from "../components/Message.vue";
export default {
  name: "Thread",
  components: {
    Message,
  },
  computed: {
    thread() {
      console.log(this.$store.state.thread, "Thread Computed");
      return this.$store.state.thread;
    },
    user() {
      return this.$store.state.loggedInUser;
    },
    isAdmin(){
      return this.user? this.user.roles.includes('ADMIN'): false
    },
    isUserAndThreadIsOpen(){
      return (this.user && this.thread.locked === false)
    }
  },
  data() {
    return {
      message: {
        messageContent: null,
        warningPost: null
      },
    };
  },
  methods: {
    async createMessage() {
      let messageToCreate = {
        messageContent: this.message.messageContent,
        warningPost: this.message.warningPost
      };

      let newMessage = await fetch(
        `/api/v1/messages/${this.thread.thread_id}`,
        {
          method: "POST",
          headers: { "Content-Type": "application/json" },
          body: JSON.stringify(messageToCreate),
        }
      );
      newMessage = await newMessage.json();
      this.$store.commit("addNewMessage", newMessage);
      this.message.messageContent = null;
      this.message.warningPost = null;
    },
  },
  async created() {
    // if (!this.thread) {
    await this.$store.dispatch("fetchThread", {
      forumId: this.$route.params.forum_id,
      threadId: this.$route.params.thread_id,
    });
    console.log(this.user, "USER I THREAD")
    // }
  },
};
</script>
<style scoped></style>
