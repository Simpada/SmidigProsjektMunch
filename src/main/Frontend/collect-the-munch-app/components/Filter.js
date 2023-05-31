import { useState } from 'react';
import { View, Text, StyleSheet, TextInput } from 'react-native';
import DropDownPicker from 'react-native-dropdown-picker';
import { colors } from '../Styles/theme';

const Filter = ({ onSearch }) => {
  const [selectedValue, setSelectedValue] = useState('');
  const [isOpen, setIsOpen] = useState(false);
  const [searchText, setSearchText] = useState('');

  const handleInputChange = (text) => {
    setSearchText(text);
    onSearch(text);
  };

  const handleOpen = () => {
    setIsOpen(!isOpen);
  };

  const handleClose = () => {
    setIsOpen(false);
    console.log(selectedValue)
  };


  return (
    <View style={styles.container}>
      <TextInput
        style={styles.textInput}
        placeholder="Search events"
        value={searchText}
        onChangeText={handleInputChange}
      />
     <DropDownPicker
        items={[
          { label: 'Historical', value: 'historical' },
          { label: 'Photography', value: 'photography' },
        ]}
        value={selectedValue} // Use value prop instead of defaultValue
        containerStyle={styles.dropdownContainer}
        style={styles.dropdown}
        itemStyle={styles.dropdownItem}
        dropDownStyle={styles.dropdownMenu}
        onChangeItem={(item) => setSelectedValue(item.value)}
        placeholder="Find by category"
        dropDownMaxHeight={200}
        activeItemStyle={styles.activeItemStyle}
        activeLabelStyle={styles.activeLabelStyle}
        onOpen={handleOpen}
        onClose={handleClose}
        open={isOpen}
      />

      {selectedValue && (
      <Text style={styles.selectedValueText}>Selected Value: {selectedValue}</Text>
      )}

    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent:"center",
    alignItems:"center",
    flexDirection: "row", 
    paddingHorizontal: 20,
    gap: 10,
  },
  textInput: {
    backgroundColor: '#fafafa',
    flex: 1,
    height: 50,
    borderRadius: 9,
    borderColor: colors.navy,
    borderWidth: 2,
    paddingHorizontal: 10,
  },
  dropdownContainer: {
    flex: 1, 
  },
  dropdown: {
    borderWidth: 2,
    borderColor: colors.navy,
    backgroundColor: '#fafafa',
  },
  dropdownItem: {
    justifyContent: 'flex-start',
  },
  dropdownMenu: {
    marginTop: 8,
    width: '80%',
  },
  activeItemStyle: {
    backgroundColor: '#e6f2ff',
  },
  activeLabelStyle: {
    color: '#000',
  },
  selectedValueText: {
    marginTop: 10,
  },
});

export default Filter;
