<template>
  <div class="container">
    <div class="row justify-content-center mt-5">
      <h1>{{ thread.title }}</h1>
    </div>

    <form @submit.prevent="createMessage" class="mb-4">
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
      <button type="submit" class="btn btn-dark">
        Skicka meddelandet
      </button>
    </form>

    <Message
      v-for="message in thread.messages"
      :key="message.message_id"
      :message="message"
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
      return this.$store.state.thread;
    },
  },
  data() {
    return {
      message: {
        messageContent: null,
      },
    };
  },
  methods: {
    async createMessage() {
      let messageToCreate = {
        messageContent: this.message.messageContent,
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
      console.log(newMessage);
    },
  },
  created() {
    // let id = this.$route.params.id;
    // if(this.thread.thread_id !== id){

    // }
    console.log(this.thread);
  },
};
</script>
<style scoped></style>
