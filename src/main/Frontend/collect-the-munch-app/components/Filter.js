import {useState} from 'react'
import { View, Text, StyleSheet, TextInput, Alert} from 'react-native'
import DropDownPicker from 'react-native-dropdown-picker';
const Filter = () => {
    const [selectedValue, setSelectedValue] = useState(null);
    const [isOpen, setIsOpen] = useState(false);

    const handleOpen = () => {
        setIsOpen(!isOpen);
      };
    const handleClose = () => {
    setIsOpen(false);
    };



    return (
        <View style={styles.container}>
            <TextInput style={styles.textInput} placeholder='Find events...'/>
            <DropDownPicker
                items={[
                { label: 'Historical', value: 'historical' },
                { label: 'Photography', value: 'photography' },
                ]}
                defaultValue={selectedValue}
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
}


const styles = StyleSheet.create({
    container: {
      height: 150,
      flexDirection: "row",
      gap: 10,
      padding: 20
    },
    textInput: {
        borderWidth:1,
        marginTop: 16,
        height: 50,
        flex: 1,
        borderColor: "#0F2335",
        backgroundColor: '#fafafa',
        borderRadius: 8,
        paddingLeft: 10
    },
    dropdownContainer: {
        flex: 1,
        width: 200,
        marginTop: 16,
        height: 40,
    },
    dropdown: {
        borderColor: "#0F2335",
        backgroundColor: '#fafafa',
    },
    dropdownItem: {
        justifyContent: 'flex-start',
    },
    dropdownMenu: {
      backgroundColor: '#fff',
    },
    selectedValueText: {
      marginTop: 16,
      fontSize: 18,
      fontWeight: 'bold',
    },
    dropdownStyle: {
      backgroundColor: '#fff',
    },
    activeItemStyle: {
      backgroundColor: '#fff',
    },
    activeLabelStyle: {
      color: 'blue',
    },
  });


export default Filter