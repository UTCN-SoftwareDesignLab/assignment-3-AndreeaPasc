<template>
  <v-dialog
    transition="dialog-bottom-transition"
    max-width="600"
    :value="opened"
  >
    <template>
      <v-card>
        <v-toolbar color="primary" dark>
          {{ isNew ? "Create patient" : "Edit patient" }}
        </v-toolbar>
        <v-form>
          <v-text-field v-model="patient.name" label="Name" />
          <v-text-field v-model="patient.identityCardNo" label="Identity Card Number" />
          <v-text-field v-model="patient.personalNumCode" label="Personal Numerical Code" />
          <v-text-field v-model="patient.address" label="Address" />
          <v-text-field v-model="patient.dateOfBirth" label="Date of Birth" />
        </v-form>
        <v-card-actions>
          <v-btn @click="persist">
            {{ isNew ? "Create" : "Save" }}
          </v-btn>
        </v-card-actions>
      </v-card>
    </template>
  </v-dialog>
</template>

<script>
import api from "../api";

export default {
  name: "PatientDialog",
  props: {
    patient: Object,
    opened: Boolean,
  },
  methods: {
    persist() {
      if (this.isNew) {
        api.patients
          .create({
            name: this.patient.name,
            address: this.patient.address,
            identityCardNo: this.patient.identityCardNo,
            dateOfBirth: this.patient.dateOfBirth,
            personalNumCode: this.patient.personalNumCode
          })
          .then(() => this.$emit("refresh"));
      } else {
        api.patients
          .edit({
            name: this.patient.name,
            address: this.patient.address,
            identityCardNo: this.patient.identityCardNo,
            dateOfBirth: this.patient.dateOfBirth,
            personalNumCode: this.patient.personalNumCode
          })
          .then(() => this.$emit("refresh"));
      }
    },
  },
  computed: {
    isNew: function () {
      return !this.patient.id;
    },
  },
};
</script>

<style scoped></style>
